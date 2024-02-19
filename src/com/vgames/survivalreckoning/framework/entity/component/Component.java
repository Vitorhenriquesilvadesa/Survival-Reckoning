package com.vgames.survivalreckoning.framework.entity.component;


import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.Transform;

public abstract class Component {

    protected GameObject parent;
    protected Transform transform;
    protected Component(GameObject parent) {
        this.parent = parent;
        this.transform = parent.transform;
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

    protected <T extends Component> T getComponent(Class<T> componentClass) {
        return parent.getComponent(componentClass);
    }

    protected <T extends Component> T addComponent(Class<T> componentClass) {
        return parent.addComponent(componentClass);
    }

    protected <T extends Component> T removeComponent(Class<T> componentClass) {
        return parent.removeComponent(componentClass);
    }
}
