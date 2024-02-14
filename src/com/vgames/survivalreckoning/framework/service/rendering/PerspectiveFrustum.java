package com.vgames.survivalreckoning.framework.service.rendering;

import com.vgames.survivalreckoning.framework.math.Matrix4f;

public class PerspectiveFrustum extends Frustum {

    private final float fov;
    private final float nearPlane;
    private final float farPlane;

    public PerspectiveFrustum(float fov, float aspectRatio, float nearPlane, float farPlane) {
        super(Matrix4f.perspective(fov, aspectRatio, nearPlane, farPlane));
        this.fov = fov;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
    }

    public float getFov() {
        return fov;
    }

    public float getNearPlane() {
        return nearPlane;
    }

    public float getFarPlane() {
        return farPlane;
    }
}
