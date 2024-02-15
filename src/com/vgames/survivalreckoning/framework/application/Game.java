package com.vgames.survivalreckoning.framework.application;

import com.vgames.survivalreckoning.framework.log.*;
import com.vgames.survivalreckoning.framework.log.annotation.*;

@LogInfo(level = LogLevel.INFO)
public abstract class Game extends Logger {

    public abstract void start();
    public abstract void update();
}
