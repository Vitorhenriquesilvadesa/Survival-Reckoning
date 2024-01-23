package com.vgames.survivalreckoning.entity;

import com.vgames.survivalreckoning.entity.component.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    private Map<Class<? extends Component>, Component> components;

    public Entity() {
        this.components = new HashMap<>();
    }

    public <T extends Component> Component addComponent(Class<T> componentClass) {
        T component;
        try {
            component = componentClass.getDeclaredConstructor().newInstance();
            this.components.put(componentClass, component);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return component;
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> componentClass) {
        return (T) this.components.get(componentClass);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T removeComponent(Class<T> componentClass) {
        Component component = this.components.get(componentClass);
        this.components.remove(componentClass);
        return (T) component;
    }

    public void update() {
        for(Component component : this.components.values()) {
            component.update();
        }
    }
}
