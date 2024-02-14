package com.vgames.survivalreckoning.framework.service.rendering.renderer;

import com.vgames.survivalreckoning.framework.service.rendering.element.model.TexturedModel;

import java.util.List;

public class RenderingPool {

    List<TexturedModel> renderingElements;

    public void pushObjectInPool(TexturedModel element) {
        this.renderingElements.add(element);
    }

    public void popObjectInPool(TexturedModel element) {
        this.renderingElements.remove(element);
    }

    public void cleanup() {
        this.renderingElements.clear();
    }
}
