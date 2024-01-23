package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogAlias;
import com.vgames.survivalreckoning.log.annotation.LogInfo;


@LogInfo(level = LogLevel.INFO)
@LogAlias(alias = "Survival Reckoning")
public class Game extends Logger {

    public void start() {
        info("Initializing now.");
    }


    public void update() {
    }
}
