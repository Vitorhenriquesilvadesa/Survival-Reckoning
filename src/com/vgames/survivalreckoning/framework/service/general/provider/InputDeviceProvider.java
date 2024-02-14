package com.vgames.survivalreckoning.framework.service.general.provider;

import com.vgames.survivalreckoning.framework.design_patterns.Singleton;
import com.vgames.survivalreckoning.framework.service.general.factory.InputDeviceFactory;
import com.vgames.survivalreckoning.framework.service.input.device.InputDevice;

public class InputDeviceProvider {
    private final InputDeviceFactory deviceFactory = new InputDeviceFactory();
    private static final Singleton<InputDeviceProvider> providerSingleton = new Singleton<>(InputDeviceProvider.class);
    public static InputDevice getDevice(DeviceType type) {
        return providerSingleton.getInstance().deviceFactory.createDevice(type);
    }
}
