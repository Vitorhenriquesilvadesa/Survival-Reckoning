package com.vgames.survivalreckoning.framework.service.event;

import com.vgames.survivalreckoning.framework.service.event.reactive.QueueDispatcher;
import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.log.LogLevel;
import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.log.annotation.LogInfo;
import com.vgames.survivalreckoning.framework.log.annotation.NotDebugLog;
import com.vgames.survivalreckoning.framework.service.event.actions.Event;

import java.util.HashMap;
import java.util.Map;

@NotDebugLog
@LogInfo(level = LogLevel.INFO)
public class EventAPI extends Logger implements ApplicationService {

    Map<EventFlag, EventCallbackFn> flags;
    QueueDispatcher queueDispatcher;

    @Override
    public boolean init() {
        this.flags = new HashMap<>();
        this.queueDispatcher = new QueueDispatcher();
        return true;
    }

    public boolean getFlag(EventFlag flag) {
        return flags.get(flag).call();
    }

    public void registerApplicationCallbackFlag(EventFlag flag, EventCallbackFn function) {
        this.flags.put(flag, function);
    }

    public void dispatchEvent(Event e) {
        queueDispatcher.dispatchEvent(e);
    }

    public void subscribe(Object object) {
        queueDispatcher.subscribe(object);
    }

    public void unsubscribe(Object object) {
        queueDispatcher.unsubscribe(object);
    }

    @Override
    public void update() {
        queueDispatcher.dispatchQueues();
    }

    @Override
    public void shutdown() {

    }
}
