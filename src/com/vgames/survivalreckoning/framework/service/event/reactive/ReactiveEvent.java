package com.vgames.survivalreckoning.framework.service.event.reactive;

public abstract class ReactiveEvent {

    boolean isHandled;

    public void setHandled(boolean handled) {
        isHandled = handled;
    }

    public boolean isHandled() {
        return isHandled;
    }
}
