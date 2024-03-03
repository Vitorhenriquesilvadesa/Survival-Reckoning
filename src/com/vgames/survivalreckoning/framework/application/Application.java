package com.vgames.survivalreckoning.framework.application;

import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.engine.EngineStarter;
import com.vgames.survivalreckoning.framework.log.LogLevel;
import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.log.annotation.LogInfo;
import com.vgames.survivalreckoning.framework.log.annotation.NotDebugLog;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.EventFlag;

@LogInfo(level = LogLevel.INFO)
@NotDebugLog
public class Application extends Logger {
    public static void init(Class<? extends Game> gameClass, String configFilepath) {
        new EngineStarter(configFilepath);
        Engine.getInstance().init(gameClass);
    }

    public static void run() {
        EventAPI eventAPI = Engine.fromService(EventAPI.class);

        while(!eventAPI.getFlag(EventFlag.WINDOW_CLOSE)) {
            Engine.getInstance().update();
        }

        Application.shutdown();
    }

    public static void shutdown() {
        Engine.getInstance().shutdown();
    }
}
