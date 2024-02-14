package com.vgames.survivalreckoning.framework.entity;


import com.vgames.survivalreckoning.framework.entity.component.Transform;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.TexturedModel;

public class Entity {

    public Transform transform;
    private TexturedModel model;
    private EntityUUID uuid;

    public Entity(TexturedModel model, Transform transform) {
        this.transform = transform;
        this.model = model;
        this.uuid = new EntityUUID();
    }

    public TexturedModel getModel() {
        return this.model;
    }
}
