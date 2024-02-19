package com.vgames.survivalreckoning.framework.service.event.reactive;

import com.vgames.survivalreckoning.framework.service.event.actions.Event;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface ListenerCallback<T extends Event> {

    void onEvent(T event);
}
