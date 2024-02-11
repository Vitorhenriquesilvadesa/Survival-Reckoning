package com.vgames.survivalreckoning.util.math;

import com.vgames.survivalreckoning.entity.component.Transform;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Matrix4f {
    private float[][] matrix;

    public Matrix4f() {
        this.matrix = new float[4][4];
        setIdentity();
    }

    public Matrix4f(float[][] matrix) {
        if (matrix.length != 4 || matrix[0].length != 4 || matrix[1].length != 4 || matrix[2].length != 4 || matrix[3].length != 4) {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }

        this.matrix = matrix;
    }

    public void setIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.matrix[i][j] = 1.0f;
                } else {
                    this.matrix[i][j] = 0.0f;
                }
            }
        }
    }

    public void translate(Vector3 translation) {
        matrix[0][3] += translation.x;
        matrix[1][3] += translation.y;
        matrix[2][3] += translation.z;
    }

    public void rotate(float angle, Vector3 axis) {
        float c = (float) Math.cos(Math.toRadians(angle));
        float s = (float) Math.sin(Math.toRadians(angle));
        float oneMinusC = 1.0f - c;

        float x = axis.x;
        float y = axis.y;
        float z = axis.z;

        matrix[0][0] = c + x * x * oneMinusC;
        matrix[1][0] = x * y * oneMinusC - z * s;
        matrix[2][0] = x * z * oneMinusC + y * s;
        matrix[0][1] = y * x * oneMinusC + z * s;
        matrix[1][1] = c + y * y * oneMinusC;
        matrix[2][1] = y * z * oneMinusC - x * s;
        matrix[0][2] = z * x * oneMinusC - y * s;
        matrix[1][2] = z * y * oneMinusC + x * s;
        matrix[2][2] = c + z * z * oneMinusC;
    }

    public void scale(Vector3 scale) {
        matrix[0][0] *= scale.x;
        matrix[1][1] *= scale.y;
        matrix[2][2] *= scale.z;
    }

    public void multiply(Matrix4f other) {
        float[][] result = new float[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = 0.0f;

                for (int k = 0; k < 4; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }

        this.matrix = result;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public float get(int row, int col) {
        return matrix[row][col];
    }


    public static Matrix4f perspective(float fov, float aspectRatio, float near, float far) {
        Matrix4f result = new Matrix4f();
        float tanHalfFov = (float) Math.tan(Math.toRadians(fov / 2.0));

        result.matrix[0][0] = 1.0f / (aspectRatio * tanHalfFov);
        result.matrix[1][1] = 1.0f / tanHalfFov;
        result.matrix[2][2] = -(far + near) / (far - near);
        result.matrix[2][3] = -1.0f;
        result.matrix[3][2] = -(2.0f * far * near) / (far - near);
        result.matrix[3][3] = 0.0f;

        return result;
    }

    public static Matrix4f getTransformationMatrix(Transform transform) {
        Matrix4f matrix = new Matrix4f();

        matrix.translate(transform.position);

        matrix.rotate(transform.rotation.x, Vector3.right());
        matrix.rotate(transform.rotation.y, Vector3.up());
        matrix.rotate(transform.rotation.z, Vector3.forward());

        matrix.scale(transform.scale);

        return matrix;
    }

    public FloatBuffer toFloatBuffer() {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);

        floatBuffer.put(matrix[0][0]).put(matrix[0][1]).put(matrix[0][2]).put(matrix[0][3]);
        floatBuffer.put(matrix[1][0]).put(matrix[1][1]).put(matrix[1][2]).put(matrix[1][3]);
        floatBuffer.put(matrix[2][0]).put(matrix[2][1]).put(matrix[2][2]).put(matrix[2][3]);
        floatBuffer.put(matrix[3][0]).put(matrix[3][1]).put(matrix[3][2]).put(matrix[3][3]);

        floatBuffer.flip();

        return floatBuffer;
    }
}
