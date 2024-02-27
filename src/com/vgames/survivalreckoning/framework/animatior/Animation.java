package com.vgames.survivalreckoning.framework.animatior;

import com.vgames.survivalreckoning.framework.Sprite.Sprite;
import com.vgames.survivalreckoning.framework.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class Animation extends Asset{
    private List<Frame> frames = new ArrayList<>();
    private ArrayList<Float> framesDuration;

    public Animation(List<Sprite> sprites, ArrayList<Float> duration) {
        if(duration.size() < sprites.size()){
            error("No such frames duration");
        }

        if(duration.size() > sprites.size()){
            warn("You have more frames than sprites");
        }

        for (int i = 0; i < sprites.size(); i ++) {
            frames.add(new Frame(sprites.get(i).texture(), duration.get(i)));
        }
        framesDuration = duration;
    }

    public void setFrameDuration(int position, float duration){
        frames.get(position).setDuration(duration);
        framesDuration.set(position,duration);
    }
    public List<Frame> getFrames() {
        return frames;
    }
    public ArrayList<Float> getFramesDuration(){return framesDuration;}
}
