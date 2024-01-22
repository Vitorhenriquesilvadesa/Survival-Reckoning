package com.vgames.survivalreckoning.service.event.actions;

public abstract class Event {

    protected final String  name;

    public Event() {
        this.name = getClass().getSimpleName();
    }

    @Override
    public abstract String toString();
}
