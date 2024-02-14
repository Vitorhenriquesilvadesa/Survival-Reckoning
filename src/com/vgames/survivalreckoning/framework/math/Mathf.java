package com.vgames.survivalreckoning.framework.math;

import com.vgames.survivalreckoning.framework.entity.component.Transform;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.Camera;

public class Mathf {

    public static final float degreesToRadians = 0.017453292519943295f;
    public static Matrix4f createTransformationMatrix(Transform transform) {
        Matrix4f matrix = new Matrix4f();

        matrix.setIdentity();

        matrix.translate(transform.position);
        matrix.rotate(transform.rotation);
        matrix.scale(transform.scale);

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        Transform cameraTransform = camera.transform;

        viewMatrix.setIdentity();
        viewMatrix.rotate(cameraTransform.rotation);

        Vector3 cameraPosition = cameraTransform.position;
        Vector3 negativeCameraPos = new Vector3(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);
        viewMatrix.translate(negativeCameraPos);

        return viewMatrix;
    }

    public static float cos(float angle) {
        return (float) Math.cos(angle);
    }

    public static float sin(float angle) {
        return (float) Math.sin(angle);
    }

    public static float tan(float angle) {
        return (float) Math.tan(angle);
    }

    public static float toRadians(float degrees) {
        return degrees * degreesToRadians;
    }
}
