package com.vgames.survivalreckoning.service.provider;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.design_patterns.Singleton;
import com.vgames.survivalreckoning.service.factory.InitializationServiceFactory;

public class ApplicationServiceProvider {
    private final InitializationServiceFactory serviceFactory = new InitializationServiceFactory();
    private static final Singleton<ApplicationServiceProvider> providerSingleton = new Singleton<>(ApplicationServiceProvider.class);
    public static ApplicationService getService(ServiceType type) {
        return providerSingleton.getInstance().serviceFactory.createService(type);
    }
}
