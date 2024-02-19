package com.vgames.survivalreckoning.framework.entity.component.collider;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;

public abstract class Collider extends Component {
    protected String tag;

    public Collider(GameObject parent) {
        super(parent);
    }
    public abstract boolean isCollision(Collider collider);

    public String getTag() {
        return tag;
    }
}
