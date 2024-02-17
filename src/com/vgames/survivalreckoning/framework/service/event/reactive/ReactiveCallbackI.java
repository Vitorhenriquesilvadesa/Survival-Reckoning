package com.vgames.survivalreckoning.framework.service.event.reactive;

import java.lang.FunctionalInterface;

public interface ReactiveCallbackI<T> {
    void onReact(T reactiveObject);
}
