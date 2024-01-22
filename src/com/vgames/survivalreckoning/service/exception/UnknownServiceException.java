package com.vgames.survivalreckoning.service.exception;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.service.provider.ServiceType;

public class UnknownServiceException extends RuntimeException {

    public UnknownServiceException(ServiceType type) {
        super("Unknown provided service: " + type.name());
    }

    public UnknownServiceException(Class<? extends ApplicationService> service) {
        super("Unknown provided service: " + service.getSimpleName());
    }
}
