package com.vgames.survivalreckoning.framework.service.event;

import com.vgames.survivalreckoning.framework.service.event.actions.Event;

public interface EventListener {
    void onEvent(Event e);
}
