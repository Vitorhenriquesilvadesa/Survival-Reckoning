package com.vgames.survivalreckoning.service.input.device;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.service.rendering.GraphicsAPI;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class MouseDevice extends InputDevice {

    float x;
    float y;
    float sensitivity;

    public MouseDevice() {
        this.x = 0f;
        this.y = 0f;
        this.sensitivity = 0.5f;
    }

    @Override
    public boolean isConnected() {
        return this.isConnected;
    }

    @Override
    public void updateState() {
        DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(Engine.fromService(GraphicsAPI.class).getNativeWindow(), mouseX, mouseY);
        this.x = (float) mouseX.get(0);
        this.y = (float) mouseY.get(0);
    }
}
