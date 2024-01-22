package com.vgames.survivalreckoning.service.input.device;

public abstract class InputDevice {

    protected boolean isConnected = true;
    public abstract boolean isConnected();
    public abstract void updateState();
}
