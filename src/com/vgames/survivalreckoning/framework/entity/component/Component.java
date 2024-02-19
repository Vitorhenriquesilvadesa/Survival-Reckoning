package com.vgames.survivalreckoning.framework.entity.component;


import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.log.Logger;

public abstract class Component extends Logger {
    protected GameObject parent;

    protected Component(GameObject parent) {
        this.parent = parent;
    }

    public void start() { }

    public void update() { }

    public void cleanup() { }
}
