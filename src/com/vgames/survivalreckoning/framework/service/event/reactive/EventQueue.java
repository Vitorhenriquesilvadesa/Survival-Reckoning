package com.vgames.survivalreckoning.framework.service.event.reactive;

import com.vgames.survivalreckoning.framework.service.event.actions.Event;
import com.vgames.survivalreckoning.framework.service.event.exception.EmptyEventQueueException;

import java.util.ArrayList;
import java.util.List;

public class EventQueue {
    private final List<Event> events;

    public EventQueue() {
        this.events = new ArrayList<>();
    }

    public void pushEvent(Event event) {
        this.events.add(event);
    }

    public void clearEvents() {
        this.events.clear();
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

    public Event popEvent() {
        if(!isEmpty()) {
            return events.getFirst();
        }

        throw new EmptyEventQueueException();
    }
}
