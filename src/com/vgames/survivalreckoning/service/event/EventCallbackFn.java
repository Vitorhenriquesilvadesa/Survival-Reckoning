package com.vgames.survivalreckoning.service.event;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface EventCallbackFn {
    boolean call();
}
