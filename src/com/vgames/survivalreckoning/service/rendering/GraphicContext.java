package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.service.event.UpdatableComponent;
import com.vgames.survivalreckoning.service.event.actions.*;
import com.vgames.survivalreckoning.service.event.exception.UnknownKeyActionException;
import com.vgames.survivalreckoning.service.event.exception.UnknownMouseActionException;
import com.vgames.survivalreckoning.service.general.exception.ServiceInitializationException;
import com.vgames.survivalreckoning.service.rendering.window.Window;
import com.vgames.survivalreckoning.service.rendering.window.WindowBuilder;

import static org.lwjgl.glfw.GLFW.*;

public class GraphicContext implements RenderingElement, UpdatableComponent {

    private final Window window;
    private final long nativeWindow;

    protected GraphicContext() {
        if(!glfwInit()) {
            throw new ServiceInitializationException(GraphicsAPI.class);
        }

        this.window = new WindowBuilder().title("Survival Reckoning").height(600).width(800).build();
        this.nativeWindow = window.getNativeWindow();
    }

    @Override
    public void render() {
        this.window.render();
    }

    public long getNativeWindow() {
        return this.nativeWindow;
    }

    public boolean isWindowClosed() {
        return glfwWindowShouldClose(this.nativeWindow);
    }

    @Override
    public void update() {
        this.window.update();
    }

    public void destroy() {
        glfwDestroyWindow(this.nativeWindow);
        glfwTerminate();
    }

    public void setCallbacks() {
        glfwSetKeyCallback(getNativeWindow(), this::keyCallback);
        glfwSetMouseButtonCallback(getNativeWindow(), this::mouseButtonCallback);
        glfwSetCursorPosCallback(getNativeWindow(), this::mouseCursorPositionCallback);
        glfwSetScrollCallback(getNativeWindow(), this::mouseScrollCallback);
        glfwSetWindowSizeCallback(getNativeWindow(), this::windowSizeCallback);
        glfwSetWindowFocusCallback(getNativeWindow(), this::windowFocusCallback);
    }

    public void windowFocusCallback(long window, boolean focused) {
        Engine.fromService(GraphicsAPI.class).onEvent(new WindowFocusEvent(focused));
    }

    public void windowSizeCallback(long window, int width, int height) {
        this.window.setWidth(width);
        this.window.setHeight(height);
        Engine.fromService(GraphicsAPI.class).onEvent(new WindowResizeEvent(width, height));
    }

    public void mouseScrollCallback(long window, double xOffset, double yOffset) {
        Engine.fromService(GraphicsAPI.class).onEvent(new MouseCursorPositionEvent((float) xOffset, (float) yOffset));
    }

    public void mouseCursorPositionCallback(long window, double xPos, double yPos) {
        glfwSetCursorPos(window, xPos, yPos);
        Engine.fromService(GraphicsAPI.class).onEvent(new MouseCursorPositionEvent((float) xPos, (float) yPos));
    }

    public void mouseButtonCallback(long window, int button, int action, int mods) {
        switch(action) {
            case GLFW_PRESS, GLFW_REPEAT: {
                Engine.fromService(GraphicsAPI.class).onEvent(new MouseButtonPressedEvent(button));
                return;
            }

            case GLFW_RELEASE: {
                Engine.fromService(GraphicsAPI.class).onEvent(new MouseButtonReleasedEvent(button));
                return;
            }

            default: {
                throw new UnknownMouseActionException(action);
            }
        }
    }

    public void keyCallback(long window, int key, int scanCode, int action, int mods) {

        switch(action) {
            case GLFW_PRESS, GLFW_REPEAT: {
                Engine.fromService(GraphicsAPI.class).onEvent(new KeyPressedEvent(key));
                return;
            }

            case GLFW_RELEASE: {
                Engine.fromService(GraphicsAPI.class).onEvent(new KeyReleasedEvent(key));
                return;
            }

            default: {
                throw new UnknownKeyActionException(action);
            }
        }
    }
}
