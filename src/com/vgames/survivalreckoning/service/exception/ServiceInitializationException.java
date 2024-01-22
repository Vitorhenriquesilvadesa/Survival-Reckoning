package com.vgames.survivalreckoning.service.exception;

import com.vgames.survivalreckoning.service.general.ApplicationService;

public class ServiceInitializationException extends RuntimeException {
    public <T extends ApplicationService> ServiceInitializationException(Class<T> klass) {
        super("Failed to initialize the " + klass.getSimpleName() + " service.");
    }
}
