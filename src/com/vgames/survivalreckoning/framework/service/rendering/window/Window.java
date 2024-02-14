package com.vgames.survivalreckoning.framework.service.rendering.window;


import com.vgames.survivalreckoning.framework.service.event.UpdatableComponent;
import com.vgames.survivalreckoning.framework.service.rendering.RenderingElement;
import com.vgames.survivalreckoning.framework.math.Vector2;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL45.*;

public class Window implements RenderingElement, UpdatableComponent {

    private int width;
    private int height;
    private final String title;
    private long nativeWindow;
    private final boolean withCoreProfile;

    public Window(int width, int height, String title, boolean withCoreProfile) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.withCoreProfile = withCoreProfile;
        initializeNative();
    }

    private void initializeNative() {
        this.nativeWindow = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
        glfwMakeContextCurrent(this.nativeWindow);
        if(withCoreProfile) {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        }
        createCapabilities();
    }

    public long getNativeWindow() {
        return this.nativeWindow;
    }

    private void clear() {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
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
    }

    @Override
    public void update() {
        swapBuffers();
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

    public Vector2 getResolution() {
        return new Vector2(this.width, this.height);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public boolean isWithCoreProfile() {
        return withCoreProfile;
    }
}
