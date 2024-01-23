package com.vgames.survivalreckoning.entity.component;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.entity.Entity;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;
import com.vgames.survivalreckoning.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.service.rendering.RenderingElement;


@LogInfo(level = LogLevel.INFO)
public abstract class RuntimeComponent extends Logger {

    protected final Entity parent;

    public RuntimeComponent(Entity parent) {
        this.parent = parent;
    }

    protected void instantiateRenderingObject(RenderingElement object) {
        Engine.fromService(GraphicsAPI.class).pushObjectInRenderingPool(object);
    }

    public abstract void update();
}
