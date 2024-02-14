package com.vgames.survivalreckoning.framework.service.input.device;

import static org.lwjgl.glfw.GLFW.glfwJoystickPresent;

public class GamepadDevice extends InputDevice {

    @Override
    public boolean isConnected() {
        return this.isConnected;
    }

    @Override
    public void updateState() {

    }
}
