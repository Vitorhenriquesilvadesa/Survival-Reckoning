package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.Sprite.Animation;
import com.vgames.survivalreckoning.framework.Sprite.Frame;
import com.vgames.survivalreckoning.framework.Time;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.log.annotation.GenerateCriticalFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@GenerateCriticalFile
public class Animator extends Component{
    private Map<String, Animation> animations = new HashMap<>();
    private Frame currentFrame;
    private String currentAnimationName;
    private int animationIndex = 0;
    private Animation animation;
    public Animator(GameObject parent){
        super(parent);
    }
    public void start(){animations = new HashMap<>();}
    public void addAnimation(Animation animation, String name){
        animations.put(name,animation);
        currentAnimationName = name;
    }
    @Override
    public void update() {
        if(animations.containsKey(currentAnimationName)){
            animation = animations.get(currentAnimationName);
            List<Frame> frames = animation.getFrames();
            currentFrame = frames.get(animationIndex);
            if(Time.deltaTime() >= currentFrame.getDuration()){
                animationIndex ++;
            }
            if(animationIndex >= frames.size()){
                animationIndex = 0;
            }
        }
    }
     public Frame getCurrentFrame(){
        return currentFrame;
     }
}
