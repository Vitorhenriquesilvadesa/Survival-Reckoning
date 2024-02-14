package com.vgames.survivalreckoning.framework.service.general.exception;

import com.vgames.survivalreckoning.framework.service.general.ApplicationService;

public class ServiceInitializationException extends RuntimeException {
    public <T extends ApplicationService> ServiceInitializationException(Class<T> klass) {
        super("Failed to initialize the " + klass.getSimpleName() + " service.");
    }
}
