package com.vgames.survivalreckoning.entity.component;

import com.vgames.survivalreckoning.log.annotation.LogAlias;
import com.vgames.survivalreckoning.util.math.Vector3;

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
}
