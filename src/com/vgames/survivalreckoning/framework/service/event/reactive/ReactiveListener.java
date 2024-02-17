package com.vgames.survivalreckoning.framework.service.event.reactive;

public interface ReactiveListener<T> {
    void onReact(ReactiveEvent<T> event);
}
