package com.vgames.survivalreckoning.framework.service.input;

import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.service.event.EventListener;
import com.vgames.survivalreckoning.framework.service.event.actions.Event;
import com.vgames.survivalreckoning.framework.service.input.exception.DisconnectedDeviceException;
import com.vgames.survivalreckoning.framework.service.input.device.InputDevice;
import com.vgames.survivalreckoning.framework.service.general.provider.DeviceType;
import com.vgames.survivalreckoning.framework.service.general.provider.InputDeviceProvider;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class InputAPI implements ApplicationService, EventListener {

    private final Map<DeviceType, InputDevice> inputDeviceMap;

    public InputAPI() {
        this.inputDeviceMap = new HashMap<>();
    }

    @Override
    public void onEvent(Event e) {

    }

    @Override
    public boolean init() {
        registerDevice(DeviceType.MOUSE);
        registerDevice(DeviceType.KEYBOARD);
        registerDevice(DeviceType.GAMEPAD);
        Input.init();
        return true;
    }

    @Override
    public void update() {
        for(InputDevice device : inputDeviceMap.values()){
            device.updateState();
        }
    }

    @Override
    public void shutdown() {

    }

    public void registerDevice(DeviceType deviceType) {
        InputDevice device = InputDeviceProvider.getDevice(deviceType);
        inputDeviceMap.put(deviceType, device);
    }

    public boolean isDeviceConnected(DeviceType deviceType) {
        return inputDeviceMap.get(deviceType).isConnected();
    }

    public boolean isKeyPressed(int keyCode) {
        if(isDeviceConnected(DeviceType.KEYBOARD)) {
            return glfwGetKey(Engine.fromService(GraphicsAPI.class).getNativeWindow(), keyCode) == 1;
        }

        throw new DisconnectedDeviceException(DeviceType.KEYBOARD);
    }

    public boolean isMouseButtonPressed(int button) {
        if(isDeviceConnected(DeviceType.MOUSE)) {
            return glfwGetMouseButton(Engine.fromService(GraphicsAPI.class).getNativeWindow(), button) == 1;
        }

        throw new DisconnectedDeviceException(DeviceType.MOUSE);
    }
}
