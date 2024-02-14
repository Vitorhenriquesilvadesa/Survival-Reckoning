package com.vgames.survivalreckoning.framework.service.general.factory;

import com.vgames.survivalreckoning.framework.service.input.device.GamepadDevice;
import com.vgames.survivalreckoning.framework.service.input.device.InputDevice;
import com.vgames.survivalreckoning.framework.service.input.device.KeyBoardDevice;
import com.vgames.survivalreckoning.framework.service.input.device.MouseDevice;
import com.vgames.survivalreckoning.framework.service.general.provider.DeviceType;

public class InputDeviceFactory {

    public InputDevice createDevice(DeviceType type) {
        return switch(type) {
            case MOUSE -> new MouseDevice();
            case KEYBOARD -> new KeyBoardDevice();
            case GAMEPAD -> new GamepadDevice();
        };
    }
}
