package com.vgames.survivalreckoning.framework.service.rendering.renderer;

import com.vgames.survivalreckoning.framework.math.Matrix4f;

public class Frustum {
    private final Matrix4f projectionMatrix;

    public Frustum(Matrix4f projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
}
