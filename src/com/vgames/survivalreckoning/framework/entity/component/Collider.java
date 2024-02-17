package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.entity.GameObject;

public abstract class Collider extends Component {
    private String tag;

    protected Collider(GameObject parent) {
        super(parent);
    }

    //public abstract Trigger onCollisionTriggerEnter(Collider collider);
    //public abstract Trigger onCollisionTriggerExit(Collider collider);
    public abstract boolean isCollision(Collider collider);
}
