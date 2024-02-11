package com.vgames.survivalreckoning.service.general.provider;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.design_patterns.Singleton;
import com.vgames.survivalreckoning.service.general.factory.ApplicationServiceFactory;

public class ApplicationServiceProvider {
    private final ApplicationServiceFactory serviceFactory = new ApplicationServiceFactory();
    private static final Singleton<ApplicationServiceProvider> providerSingleton = new Singleton<>(ApplicationServiceProvider.class);
    public static ApplicationService getService(ServiceType type) {
        return providerSingleton.getInstance().serviceFactory.createService(type);
    }
}
