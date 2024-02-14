package com.vgames.survivalreckoning.framework.service.input.device;

import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;

import static org.lwjgl.glfw.GLFW.*;

public class KeyBoardDevice extends InputDevice {

    public KeyBoardDevice() {
    }

    @Override
    public boolean isConnected() {
        return this.isConnected;
    }

    @Override
    public void updateState() {

    }
}
