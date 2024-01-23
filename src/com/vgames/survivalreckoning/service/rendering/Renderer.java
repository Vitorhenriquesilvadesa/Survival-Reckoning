package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.entity.component.SpriteRendererComponent;
import com.vgames.survivalreckoning.service.rendering.annotation.ShaderName;
import com.vgames.survivalreckoning.service.rendering.exception.UnknownShaderException;
import com.vgames.survivalreckoning.service.rendering.pipeline.ShaderPipeline;

import java.util.Map;

public class Renderer extends AbstractRenderer {

    RenderQueue renderQueue;

    Map<ShaderPipeline, RenderingElement> elementMap;

    public Renderer() {
        this.renderQueue = new RenderQueue();
    }

    public void render(RenderingElement object) {
        if(object.getClass().isAnnotationPresent(ShaderName.class)) {
            switch(object.getClass().getDeclaredAnnotation(ShaderName.class).name()) {
                case "texture" -> renderTexture(object);
                case "directional_light" -> renderDirectionalLight(object);
                default -> throw new UnknownShaderException();
            }
        } else {
            renderTexture(object);
        }
    }

    @Override
    public void renderTexture(RenderingElement object) {
        SpriteRendererComponent rendererComponent = (SpriteRendererComponent) object;
        return;
    }

    @Override
    public void renderDirectionalLight(RenderingElement element) {
        return;
    }

    public void addObjectToQueue(RenderingElement object) {
        renderQueue.attachObject(object);
    }
}
