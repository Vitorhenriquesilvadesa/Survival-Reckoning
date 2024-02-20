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
    private int animationIndex = 0;
    private Texture currentTexture;
    private AnimationFixedTime time = new AnimationFixedTime();
    private float frameCount = 0;
    private float currentFrameElapseTime = 0f;
    private float maxFrameDuration = 0f;
    private boolean isPaused = false;
    public Animator(GameObject parent) {
        super(parent);
    }
    public void start(){
        animations = new HashMap<>();
    }

    public void playAnimation(String name){
        updateCurrentAnimationFrames(name);
        updateCurrentFrameCount(name);
        animationIndex = 0;
        maxFrameDuration = currentAnimationFrames.getFirst().getDuration() / 16f;

        isPaused = false;
    }
    public void puaseAnimation(String name){
        currentTexture = animations.get(name).getFrames().getFirst().getTexture();
        isPaused = true;
    }
    public void addAnimation(Animation animation,String name){
        if(animations.containsKey(name)){
            System.out.println("Texture already exist's");
        }else {
            animations.put(name, animation);
            currentTexture = animation.getFrames().getFirst().getTexture();
            getComponent(SpriteRenderer.class).setTexture(currentTexture);
            playAnimation(name);
        }
    }
    public void deleteAnimation(String name){
        animations.remove(name);
    }
    @Override
    public void update(){

        currentFrameElapseTime += Time.deltaTime();

        if(!isPaused){
            System.out.println(Time.deltaTime());
            if(currentFrameElapseTime  >= maxFrameDuration){
                if(animationIndex < frameCount -1){
                    animationIndex ++;
                    maxFrameDuration = currentAnimationFrames.get(animationIndex).getDuration() / 20f;
                    currentFrameElapseTime = 0f;
                    currentTexture = currentAnimationFrames.get(animationIndex).getTexture();
                }else {
                    animationIndex = 0;
                }
            }
        }
        getComponent(SpriteRenderer.class).setTexture(currentTexture);
    }

    private void updateCurrentAnimationFrames(String name){
        currentAnimationFrames = new ArrayList<>(animations.get(name).getFrames());
    }
    private void updateCurrentFrameCount(String name){
        frameCount = animations.get(name).getFrames().size();
    }
}