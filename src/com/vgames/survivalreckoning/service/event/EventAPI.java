package com.vgames.survivalreckoning.service.event;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;
import com.vgames.survivalreckoning.log.annotation.NotDebugLog;
import com.vgames.survivalreckoning.service.event.actions.Event;

import java.util.HashMap;
import java.util.Map;

@NotDebugLog
@LogInfo(level = LogLevel.INFO)
public class EventAPI extends Logger implements ApplicationService {

    Map<EventFlag, EventCallbackFn> flags;

    @Override
    public boolean init() {
        this.flags = new HashMap<>();
        return true;
    }

    public boolean getFlag(EventFlag flag) {
        return flags.get(flag).call();
    }

    public void registerApplicationCallbackFlag(EventFlag flag, EventCallbackFn function) {
        this.flags.put(flag, function);
    }

    public void dispatchEvent(Event e) {
        trace(e.toString());
    }

    @Override
    public void update() {

    }

    @Override
    public void shutdown() {

    }
}
