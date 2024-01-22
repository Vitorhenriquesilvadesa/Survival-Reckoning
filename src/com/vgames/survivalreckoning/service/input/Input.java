package com.vgames.survivalreckoning.service.input;

import com.vgames.survivalreckoning.engine.Engine;

public class Input {

    private static InputAPI inputAPI;

    protected static void init() {
        inputAPI = Engine.fromService(InputAPI.class);
    }

    public static boolean isKeyPressed(int keyCode) {
        return inputAPI.isKeyPressed(keyCode);
    }

    public static boolean isKeyReleased(int keyCode) {
        return !isKeyPressed(keyCode);
    }

    public static boolean isMouseButtonPressed(int button) {
        return inputAPI.isMouseButtonPressed(button);
    }

    public static boolean isMouseButtonReleased(int button) {
        return !isMouseButtonPressed(button);
    }
}
