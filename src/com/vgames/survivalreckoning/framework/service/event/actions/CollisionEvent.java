package com.vgames.survivalreckoning.framework.service.event.actions;

import com.vgames.survivalreckoning.framework.entity.component.collider.Collider;
import com.vgames.survivalreckoning.framework.service.event.reactive.ReactiveEvent;

public class CollisionEvent extends ReactiveEvent {

    public final Collider firstCollider;
    public final Collider secondCollider;

    public CollisionEvent(Collider firstCollider, Collider secondCollider) {
        this.firstCollider = firstCollider;
        this.secondCollider = secondCollider;
    }

    @Override
    public String toString() {
        return "Collision Event";
    }
}
