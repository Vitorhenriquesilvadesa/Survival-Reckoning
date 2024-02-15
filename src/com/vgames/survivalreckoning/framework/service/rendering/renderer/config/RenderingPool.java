package com.vgames.survivalreckoning.framework.service.rendering.renderer.config;

import com.vgames.survivalreckoning.framework.service.rendering.element.model.Model;

import java.util.List;

public class RenderingPool {

    List<Model> renderingElements;

    public void pushObjectInPool(Model element) {
        this.renderingElements.add(element);
    }

    public void popObjectInPool(Model element) {
        this.renderingElements.remove(element);
    }

    public void cleanup() {
        this.renderingElements.clear();
    }
}
