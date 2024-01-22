package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;
import com.vgames.survivalreckoning.log.annotation.NotDebugLog;
import com.vgames.survivalreckoning.service.event.EventAPI;
import com.vgames.survivalreckoning.service.event.EventFlag;
import com.vgames.survivalreckoning.service.input.Input;
import com.vgames.survivalreckoning.service.input.KeyCode;

@LogInfo(level = LogLevel.INFO)
@NotDebugLog
public class Application extends Logger {
    public static void init() {
        Engine.init();
    }

    public static void run() {
        EventAPI eventAPI = Engine.fromService(EventAPI.class);

        while(!eventAPI.getFlag(EventFlag.WINDOW_CLOSE)) {
            Engine.getInstance().updateServices();
        }

        Application.shutdown();
    }

    public static void shutdown() {
        Engine.getInstance().shutdown();
    }
}
