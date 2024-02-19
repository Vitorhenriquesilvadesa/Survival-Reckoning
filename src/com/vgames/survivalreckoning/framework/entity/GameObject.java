package com.vgames.survivalreckoning.framework.entity;


public class GameObject extends ComponentContainer {

    public Transform transform;
    private GameObjectUUID uuid;

    public GameObject() {
        this.transform = new Transform();
        this.uuid = new GameObjectUUID();
        setOwner(this);
    }

    public GameObject(Transform transform) {
        this.transform = transform;
        this.uuid = new GameObjectUUID();
        setOwner(this);
    }
}
