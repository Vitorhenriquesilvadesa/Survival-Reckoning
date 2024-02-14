package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.log.annotation.LogAlias;
import com.vgames.survivalreckoning.framework.math.Vector3;

@LogAlias("Transform")
public class Transform {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Transform() {
        this.position = Vector3.zero();
        this.rotation = Vector3.zero();
        this.scale = new Vector3(1f, 1f, 1f);
    }

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
