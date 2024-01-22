package com.vgames.survivalreckoning.service.event;

import com.vgames.survivalreckoning.service.event.actions.Event;

public interface EventListener {
    void onEvent(Event e);
}
