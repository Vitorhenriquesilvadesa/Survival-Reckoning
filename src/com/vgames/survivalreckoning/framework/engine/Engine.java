package com.vgames.survivalreckoning.framework.engine;


import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.setting.*;
import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.design_patterns.Singleton;
import com.vgames.survivalreckoning.framework.log.*;
import com.vgames.survivalreckoning.framework.log.annotation.*;
import com.vgames.survivalreckoning.framework.service.general.provider.ApplicationServiceProvider;
import com.vgames.survivalreckoning.framework.service.general.provider.ServiceType;
import com.vgames.survivalreckoning.framework.service.general.exception.UnknownServiceException;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.EventFlag;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@LogInfo(level = LogLevel.INFO)
@LogAlias("Application")
@GenerateCriticalFile
public class Engine extends Logger {
    private static final Singleton<Engine> engineSingleton = new Singleton<>(Engine.class);
    private final Map<ServiceType, ApplicationService> services;
    private boolean successfulInitialization;
    private int callsPerSecond;
    private float timeBetweenCalls;
    private long lastUpdateTime;
    private Game game;
    private WindowSettings windowSettings;
    private GearSettings gearSettings;
    private DebugSettings debugSettings;
    private static String title;
    private static String rootDirectory;
    private Class<? extends Game> gameClass;

    public void init(Class<? extends Game> gameClass) {

        title = windowSettings.getWindowTitle() == null ? "Gear Window" : windowSettings.getWindowTitle();
        callsPerSecond = gearSettings.getMaxTicksPerSecond() == null ? 60 : gearSettings.getMaxTicksPerSecond();
        rootDirectory = gearSettings.getRootDirectory() == null ? "" : gearSettings.getRootDirectory();

        Logger.generateCriticalFiles = debugSettings.isGenerateCriticalFiles() == null || debugSettings.isGenerateCriticalFiles();
        Logger.globalDebugDefinition = debugSettings.isShowLogs() == null || debugSettings.isShowLogs();
        Logger.enableFileTracking = debugSettings.isEnableFileTracking() == null || debugSettings.isEnableFileTracking();

        successfulInitialization = true;

        boolean gameInitializationFromConfigFile = gearSettings.getGameClass() != null;

        if(gameInitializationFromConfigFile) {
            this.gameClass = gearSettings.getGameClass();
        } else {
            this.gameClass = gameClass;
        }

        initializeServices();
        initializationLog();
        setEventFlags();

        GraphicsAPI graphicsAPI = fromService(GraphicsAPI.class);

        graphicsAPI.setViewportSize(windowSettings.getWindowSize().x, windowSettings.getWindowSize().y);
        graphicsAPI.setFullScreen(windowSettings.isFullScreen());

        try {
            game = this.gameClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            critical("", new RuntimeException(e));
        }

        timeBetweenCalls = 1f / callsPerSecond;
        lastUpdateTime = System.nanoTime();

        game.start();
    }

    public Engine() {
        this.services = new HashMap<>();
    }

    public void shutdown() {
        breakLine();
        for(ApplicationService service : engineSingleton.getInstance().services.values()) {
            warn("Shutdown the " + service.getClass().getSimpleName() +
                    " ".repeat(15 - service.getClass().getSimpleName().length()) + " service.");
            service.shutdown();
        }
    }

    private void initializeServices() {
        registerServices();
        StringBuilder builder = new StringBuilder();

        for(ApplicationService service : this.services.values()) {
            boolean initializeResult = service.init();
            info("Initializing the " + service.getClass().getSimpleName() +
                    " ".repeat(15 - service.getClass().getSimpleName().length()) +
                    " service. Result: " + (initializeResult ? "Success." : "Failed."));

            if(!initializeResult) builder.append(" ").append(service.getClass().getSimpleName());

            if(!initializeResult) {
                engineSingleton.getInstance().successfulInitialization = false;
                critical("Failed to initialize services: " + builder, new IllegalStateException());
            }
        }
    }

    private void registerServices() {
        registerService(ServiceType.EVENT_API);
        registerService(ServiceType.GRAPHICS_API);
        registerService(ServiceType.AUDIO_API);
        registerService(ServiceType.INPUT_API);
        registerService(ServiceType.POOL_API);
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
            breakLine();
            info("Successful to initialize services.");
            breakLine();
        } else {
            shutdown();
            critical("Failed to initialize services.", new RuntimeException());
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

    public int maxUpdateCallPerSecond() {
        return callsPerSecond;
    }

    public void update() {
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastUpdateTime) / 1_000_000_000.0f; // Converte para segundos
        if (deltaTime >= timeBetweenCalls) {
            updateGame();
            updateServices();
            updateTime();
            lastUpdateTime = currentTime;
        }
    }

    private void updateTime() {
        Time.update();
    }

    private void updateGame() {
        game.update();
    }

    public static String getTitle() {
        return title;
    }

    void setWindowSettings(WindowSettings windowSettings) {
        this.windowSettings = windowSettings;
    }

    void setGearSettings(GearSettings gearSettings) {
        this.gearSettings = gearSettings;
    }

    void setDebugSettings(DebugSettings debugSettings) {
        this.debugSettings = debugSettings;
    }
}
