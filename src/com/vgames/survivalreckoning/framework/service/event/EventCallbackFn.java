package com.vgames.survivalreckoning.framework.service.event;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface EventCallbackFn {
    boolean call();
}
