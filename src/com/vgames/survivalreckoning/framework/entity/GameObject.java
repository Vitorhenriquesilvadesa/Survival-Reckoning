package com.vgames.survivalreckoning.framework.entity;


import com.vgames.survivalreckoning.framework.entity.component.Animator;
import com.vgames.survivalreckoning.framework.entity.component.ComponentContainer;
import com.vgames.survivalreckoning.framework.entity.component.Transform;

public class GameObject extends ComponentContainer {

    public Transform transform;
    private GameUUID uuid;
    public Animator animator;

    public GameObject() {
        this.transform = new Transform();
        this.uuid = new GameUUID();
        this.animator = new Animator(this);
        setOwner(this);
    }

    public GameObject(Transform transform) {
        this.transform = transform;
        this.uuid = new GameUUID();
        setOwner(this);
    }
}
