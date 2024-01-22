package com.vgames.survivalreckoning.engine;


import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.design_patterns.Singleton;
import com.vgames.survivalreckoning.log.*;
import com.vgames.survivalreckoning.log.annotation.*;
import com.vgames.survivalreckoning.service.provider.ApplicationServiceProvider;
import com.vgames.survivalreckoning.service.provider.ServiceType;
import com.vgames.survivalreckoning.service.exception.UnknownServiceException;
import com.vgames.survivalreckoning.service.event.EventAPI;
import com.vgames.survivalreckoning.service.event.EventFlag;
import com.vgames.survivalreckoning.service.rendering.GraphicsAPI;

import java.util.HashMap;
import java.util.Map;

@LogInfo(level = LogLevel.INFO, verbose = true)
public class Engine extends Logger {
    private static final Singleton<Engine> engineSingleton = new Singleton<>(Engine.class);
    private final Map<ServiceType, ApplicationService> services;
    private boolean successfulInitialization;

    public static void init() {
        getInstance().successfulInitialization = true;
        getInstance().initializeServices();
        getInstance().initializationLog();
        getInstance().setEventFlags();
    }

    public Engine() {
        this.services = new HashMap<>();
    }

    public void shutdown() {
        for(ApplicationService service : engineSingleton.getInstance().services.values()) {
            info("Shutdown the " + service.getClass().getSimpleName() +
                    " ".repeat(12 - service.getClass().getSimpleName().length()) + " service.");
            service.shutdown();
        }
    }

    private void initializeServices() {
        registerServices();
        for(ApplicationService service : this.services.values()) {
            boolean initializeResult = service.init();
            info("Initializing the " + service.getClass().getSimpleName() +
                    " ".repeat(12 - service.getClass().getSimpleName().length()) +
                    " service. Result: " + (initializeResult ? "Success." : "Failed."));

            if(!initializeResult) {
                engineSingleton.getInstance().successfulInitialization = false;
            }
        }

        breakLine();
    }

    private void registerServices() {
        registerService(ServiceType.EVENT_API);
        registerService(ServiceType.GRAPHICS_API);
        registerService(ServiceType.AUDIO_API);
        registerService(ServiceType.INPUT_API);
    }

    private void setEventFlags() {
        EventAPI eventAPI = Engine.fromService(EventAPI.class);
        GraphicsAPI graphicsAPI = Engine.fromService(GraphicsAPI.class);
        eventAPI.registerApplicationCallbackFlag(EventFlag.WINDOW_CLOSE, graphicsAPI::checkWindowClosed);
    }

    public void updateServices() {
        for(ApplicationService service : this.services.values()) {
            service.update();
        }
    }

    private void registerService(ServiceType type) {
        this.services.put(type, ApplicationServiceProvider.getService(type));
    }

    private void initializationLog() {
        if(getInstance().successfulInitialization) {
            info("Successful to initialize services.");
        } else {
            error("Failed to initialize services.", new RuntimeException());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends ApplicationService> T fromService(Class<T> klass) {
        for(ApplicationService service : getInstance().services.values()) {
            if(service.getClass() == klass) {
                return (T) service;
            }
        }

        throw new UnknownServiceException(klass);
    }

    public static Engine getInstance() {
        return engineSingleton.getInstance();
    }
}
