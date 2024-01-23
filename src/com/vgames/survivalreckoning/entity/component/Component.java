package com.vgames.survivalreckoning.entity.component;

import com.vgames.survivalreckoning.entity.Entity;

public abstract class Component extends RuntimeComponent {
    public Component(Entity parent) {
        super(parent);
    }
}
