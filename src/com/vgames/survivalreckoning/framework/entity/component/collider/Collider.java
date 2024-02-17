package com.vgames.survivalreckoning.framework.entity.component.collider;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;

public abstract class Collider extends Component {
    private String tag;

    protected Collider(GameObject parent) {
        super(parent);
    }

    //public abstract Trigger onCollisionTriggerEnter(Collider collider);
    //public abstract Trigger onCollisionTriggerExit(Collider collider);
    public abstract boolean isCollision(Collider collider);
}
