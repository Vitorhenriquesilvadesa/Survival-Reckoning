package com.vgames.survivalreckoning.service.rendering.element;

import com.vgames.survivalreckoning.entity.component.TransformComponent;

public class Sprite {
    TransformComponent parent;
    Texture texture;

    public Sprite(TransformComponent parent, String textureFilePath) {
        this.parent = parent;
        this.texture = Texture.loadFrom(textureFilePath);
    }

    public void setParent(TransformComponent parent) {
        this.parent = parent;
    }
}
