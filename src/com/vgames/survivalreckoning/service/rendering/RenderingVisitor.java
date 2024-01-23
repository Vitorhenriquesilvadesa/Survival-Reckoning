package com.vgames.survivalreckoning.service.rendering;

public interface RenderingVisitor {

    public abstract void renderTexture(RenderingElement element);
    public abstract void renderDirectionalLight(RenderingElement element);
}
