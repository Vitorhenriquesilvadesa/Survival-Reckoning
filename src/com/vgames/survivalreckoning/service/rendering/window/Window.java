package com.vgames.survivalreckoning.service.rendering.window;


import com.vgames.survivalreckoning.service.event.UpdatableComponent;
import com.vgames.survivalreckoning.service.rendering.RenderingElement;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL45.*;

public class Window implements RenderingElement, UpdatableComponent {

    private int width;
    private int height;
    private String title;
    private long nativeWindow;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        initializeNative();
    }

    private void initializeNative() {
        this.nativeWindow = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
        glfwMakeContextCurrent(this.nativeWindow);
        createCapabilities();
    }

    public long getNativeWindow() {
        return this.nativeWindow;
    }

    private void clear() {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
    }

    private void swapBuffers() {
        glfwSwapBuffers(this.nativeWindow);
    }

    private void pollEvents() {
        glfwPollEvents();
    }

    @Override
    public void render() {
        clear();
        swapBuffers();
    }

    @Override
    public void update() {
        pollEvents();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }
}
