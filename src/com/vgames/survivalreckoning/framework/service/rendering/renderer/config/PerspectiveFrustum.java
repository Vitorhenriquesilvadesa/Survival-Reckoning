package com.vgames.survivalreckoning.framework.service.rendering.renderer.config;

import com.vgames.survivalreckoning.framework.math.Matrix4f;

public class PerspectiveFrustum extends Frustum {

    public PerspectiveFrustum(float angleOfView, float near, float far) {
        super(Matrix4f.perspective(angleOfView, near, far));
    }
}
