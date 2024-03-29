package com.vgames.survivalreckoning.framework.entity.component.spriterenderer;

import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;

public class Frame {
    private Texture texture;
    private float duration;

    public Frame(Texture texture, float duration) {
        this.texture = texture;
        this.duration = duration;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getDuration() {
        return duration;
    }
}
