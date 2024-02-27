package com.vgames.survivalreckoning.framework.entity.component.collider;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;

public class BoxCollider2D extends Collider {
    private Box2DMesh mesh;

    public BoxCollider2D(GameObject parent) {
        super(parent);
        this.tag = "BoxCollider2D";
    }

    public void start() {
        mesh = getComponent(Box2DMesh.class);
    }


    @Override
    public boolean isCollision(Collider collider) {
        return false;
    }
}
