package com.vgames.survivalreckoning.service.input.device;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.service.rendering.GraphicsAPI;

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
