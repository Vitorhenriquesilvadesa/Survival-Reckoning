package com.vgames.survivalreckoning.service.input.exception;

import com.vgames.survivalreckoning.service.provider.DeviceType;

public class DisconnectedDeviceException extends RuntimeException {
    public DisconnectedDeviceException(DeviceType deviceType) {
        super("Provided device is not connected: " + deviceType.name());
    }
}
