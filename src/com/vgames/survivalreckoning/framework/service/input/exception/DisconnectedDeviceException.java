package com.vgames.survivalreckoning.framework.service.input.exception;

import com.vgames.survivalreckoning.framework.service.general.provider.DeviceType;

public class DisconnectedDeviceException extends RuntimeException {
    public DisconnectedDeviceException(DeviceType deviceType) {
        super("Provided device is not connected: " + deviceType.name());
    }
}
