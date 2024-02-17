package com.vgames.survivalreckoning.framework.entity.component;


import com.vgames.survivalreckoning.framework.entity.GameObject;

public abstract class Component {
    protected GameObject parent;
    protected Component(GameObject parent) {
        this.parent = parent;
    }

    public void start() { }

    public void update() { }

    public void cleanup() { }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
