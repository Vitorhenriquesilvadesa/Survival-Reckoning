package com.vgames.survivalreckoning.framework.service.general.provider;

import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.design_patterns.Singleton;
import com.vgames.survivalreckoning.framework.service.general.factory.ApplicationServiceFactory;

public class ApplicationServiceProvider {
    private final ApplicationServiceFactory serviceFactory = new ApplicationServiceFactory();
    private static final Singleton<ApplicationServiceProvider> providerSingleton = new Singleton<>(ApplicationServiceProvider.class);
    public static ApplicationService getService(ServiceType type) {
        return providerSingleton.getInstance().serviceFactory.createService(type);
    }
}
