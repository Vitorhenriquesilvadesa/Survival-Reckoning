package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;

public class BoxCollider2D extends Collider {
    private Box2DMesh mesh;

    protected BoxCollider2D(GameObject parent) {
        super(parent);
    }


    @Override
    public boolean isCollision(Collider collider) {
        return false;
    }
}
