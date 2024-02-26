package com.vgames.survivalreckoning.framework.animatior;

import com.vgames.survivalreckoning.framework.entity.component.spriterenderer.SpriteRenderer;
import com.vgames.survivalreckoning.framework.engine.Time;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.log.LogLevel;
import com.vgames.survivalreckoning.framework.log.annotation.LogAlias;
import com.vgames.survivalreckoning.framework.log.annotation.LogInfo;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@LogInfo(level = LogLevel.INFO, verbose = true)
@LogAlias("Animator")

public class Animator extends Component {
    private Map<String,Animation> animations;
    private List<Frame> currentAnimationFrames;
    private ArrayList<Float> currentFrameDuration;
    private int animationIndex;
    private Texture currentTexture;
    private int frameCount;
    private float currentFrameElapseTime = 0f;
    private float maxFrameDuration = 0f;
    private boolean isPaused = false;
    private int animationSpeed;
    private boolean isPlaying = false;
    public Animator(GameObject parent) {
        super(parent);
    }
    public void start(){
        animations = new HashMap<>();
    }

    public void playAnimation(String name){
        if(!isPlaying){
            isPlaying = true;
            Animation animation = animations.get(name);
            updateCurrentAnimationFrames(animation);
            updateCurrentFrameCount(animation);
            updateCurrentFrameDurationList(animation);
            animationIndex = 0;
            maxFrameDuration = currentAnimationFrames.getFirst().getDuration() / animationSpeed;
            isPaused = false;
        }
    }
    public void puaseAnimation(){
        isPaused = true;
        isPlaying = false;
    }
    public void addAnimation(Animation animation,String name,int animationSpeed){
        if(animations.containsKey(name)){
            System.out.println("Texture already exist's");
        }else {
            animations.put(name, animation);
            currentTexture = animation.getFrames().getFirst().getTexture();
            this.animationSpeed = animationSpeed;
            getComponent(SpriteRenderer.class).setTexture(currentTexture);
            playAnimation(name);
        }
    }
    public void deleteAnimation(String name){
        animations.remove(name);
    }
    @Override
    public void update(){
        jumpToNextFrame();
        getComponent(SpriteRenderer.class).setTexture(currentTexture);
    }
    private void jumpToNextFrame(){
        currentFrameElapseTime += Time.deltaTime();
        if(!isPaused){
            if(currentFrameElapseTime  >= maxFrameDuration){
                if(animationIndex < frameCount){
                    currentTexture = currentAnimationFrames.get(animationIndex).getTexture();
                    maxFrameDuration = currentFrameDuration.get(animationIndex) / animationSpeed;
                    animationIndex ++;
                    currentFrameElapseTime = 0f;
                }else {
                    isPlaying = false;
                    animationIndex = 0;
                }
            }
        }

    }
    private void updateCurrentAnimationFrames(Animation animation){
        currentAnimationFrames = new ArrayList<>(animation.getFrames());
    }
    private void updateCurrentFrameCount(Animation animation){
        frameCount = animation.getFrames().size();
    }
    private void updateCurrentFrameDurationList(Animation animation){
       currentFrameDuration = animation.getFramesDuration();
    }
}