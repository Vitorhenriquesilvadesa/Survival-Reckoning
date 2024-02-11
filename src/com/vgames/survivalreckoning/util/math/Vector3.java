package com.vgames.survivalreckoning.util.math;

public class Vector3 {

    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this.x = 0f;
        this.y = 0f;
        this.z = 0f;
    }

    public Vector3(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Vector2 xy() {
        return new Vector2(this.x, this.y);
    }

    public static Vector3 zero() {
        return new Vector3(0f, 0f, 0f);
    }

    public static Vector3 normalize(Vector3 vector) {
        float length = length(vector);

        if (length != 0.0f) {
            vector.x /= length;
            vector.y /= length;
            vector.z /= length;
        }

        return vector;
    }

    public static float length(Vector3 vector) {
        return (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z);
    }

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 subtract(Vector3 a, Vector3 b) {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 multiply(Vector3 vector, float scalar) {
        return new Vector3(vector.x * scalar, vector.y * scalar, vector.z * scalar);
    }

    public static Vector3 divide(Vector3 vector, float scalar) {

        if (scalar != 0.0f) {
            return new Vector3(vector.x / scalar, vector.y / scalar, vector.z / scalar);
        } else {
            return zero();
        }
    }

    public static Vector3 forward() {
        return new Vector3(0f, 0f, -1f);
    }

    public static Vector3 back() {
        return new Vector3(0f, 0f, 1f);
    }

    public static Vector3 left() {
        return new Vector3(-1f, 0f, 0f);
    }

    public static Vector3 right() {
        return new Vector3(1f, 0f, 0f);
    }

    public static Vector3 up() {
        return new Vector3(0f, 1f, 0f);
    }

    public static Vector3 down() {
        return new Vector3(0f, -1f, 0f);
    }
}
