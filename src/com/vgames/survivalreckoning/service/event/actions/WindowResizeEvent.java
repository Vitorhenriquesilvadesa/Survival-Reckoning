package com.vgames.survivalreckoning.service.event.actions;

public class WindowResizeEvent extends Event {

    int width;
    int height;

    public WindowResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return this.name + ": size{" + this.width + ", " + this.height + "}";
    }
}
