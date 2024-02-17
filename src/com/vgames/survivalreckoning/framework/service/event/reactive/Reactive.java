package com.vgames.survivalreckoning.framework.service.event.reactive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Reactive<T> {

    private List<ReactiveListener<T>> listeners;
    private Collection<T> data;

    public Reactive() {
        this.listeners = new ArrayList<>();
    }

    public void notifyListeners(ReactiveEvent<T> event) {
        for(ReactiveListener<T> listener : this.listeners) {
            listener.onReact(event);
        }
    }

    public void subscribe(ReactiveListener<T> listener) {
        this.listeners.add(listener);
    }

    public void unsubscribe(ReactiveListener<T> listener) {
        this.listeners.remove(listener);
    }

    public abstract void onEvent(ReactiveEvent<T> event);

    public Collection<T> getData() {
        return data;
    }
}
