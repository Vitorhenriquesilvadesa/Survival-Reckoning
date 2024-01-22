package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;
import com.vgames.survivalreckoning.service.event.EventAPI;
import com.vgames.survivalreckoning.service.event.EventListener;
import com.vgames.survivalreckoning.service.event.actions.Event;

@LogInfo(level = LogLevel.INFO)
public class GraphicsAPI extends Logger implements ApplicationService, EventListener {

    private GraphicContext graphicsContext;

    @Override
    public boolean init() {
        this.graphicsContext = new GraphicContext();
        this.graphicsContext.setCallbacks();
        return true;
    }

    private void setCallbacks() {
        this.graphicsContext.setCallbacks();
    }

    @Override
    public void update() {
        graphicsContext.render();
        graphicsContext.update();
    }

    @Override
    public void shutdown() {
        graphicsContext.destroy();
    }

    public boolean checkWindowClosed() {
        return this.graphicsContext.isWindowClosed();
    }

    public long getNativeWindow() {
        return this.graphicsContext.getNativeWindow();
    }

    @Override
    public void onEvent(Event e) {
        Engine.fromService(EventAPI.class).dispatchEvent(e);
    }
}
