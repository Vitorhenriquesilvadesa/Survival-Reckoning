package com.vgames.survivalreckoning.service.event.actions;

public class MouseCursorPositionEvent extends Event {

    private float x;
    private float y;

    public MouseCursorPositionEvent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.name + ": pos{" + this.x + ", " + this.y + "}";
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
