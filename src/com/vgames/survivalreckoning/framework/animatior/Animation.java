package com.vgames.survivalreckoning.framework.animatior;

import com.vgames.survivalreckoning.framework.Sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<Frame> frames = new ArrayList<>();

    public Animation(List<Sprite> sprites, float duration) {
        for (Sprite sprite : sprites) {
            frames.add(new Frame(sprite.texture(), duration));
        }
    }

    public void setFrameDuration(int position, int duration){
        frames.get(position).setDuration(duration);
    }
    public List<Frame> getFrames() {
        return frames;
    }
}
