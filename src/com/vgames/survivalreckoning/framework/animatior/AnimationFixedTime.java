package com.vgames.survivalreckoning.framework.animatior;

public class AnimationFixedTime {
    private float deltaTime;
    private long currentTime;
    private long previousTime;

    public  void update() {
        deltaTime += 0.01f;
    }
    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }
    private void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getPreviousTime() {
        return previousTime;
    }
}