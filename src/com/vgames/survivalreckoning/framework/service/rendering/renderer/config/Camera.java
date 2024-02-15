package com.vgames.survivalreckoning.framework.service.rendering.renderer.config;

import com.vgames.survivalreckoning.framework.entity.component.Transform;
import com.vgames.survivalreckoning.framework.math.Vector3;

public class Camera {

    public Transform transform;

    public Camera() {
        this.transform = new Transform();
    }

    public Camera(Transform transform) {
        this.transform = transform;
    }

    public Camera(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.transform = new Transform(position, rotation, scale);
    }
}
