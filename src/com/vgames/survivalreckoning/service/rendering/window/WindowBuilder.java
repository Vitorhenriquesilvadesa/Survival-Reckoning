package com.vgames.survivalreckoning.service.rendering.window;

public class WindowBuilder {

    private int width;
    private int height;
    private String title;

    public WindowBuilder() {

    }

    public WindowBuilder width(int width) {
        this.width = width;
        return this;
    }

    public WindowBuilder height(int height) {
        this.height = height;
        return this;
    }

    public WindowBuilder title(String title) {
        this.title = title;
        return this;
    }

    public Window build() {
        return new Window(width, height, title);
    }
}
