package com.vgames.survivalreckoning.framework.service.event.reactive;

import java.util.Collection;

public class ReactiveEvent<T> {

    private Reactive<T> callback;
    private Collection<T> data;

    public ReactiveEvent(Reactive<T> callback, Collection<T> data) {
        this.callback = callback;
        this.data = data;
    }

    public Reactive<T> getCallback() {
        return callback;
    }

    public Collection<T> getData() {
        return data;
    }
}
