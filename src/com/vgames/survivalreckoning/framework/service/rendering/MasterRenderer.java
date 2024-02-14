package com.vgames.survivalreckoning.framework.service.rendering;

import com.vgames.survivalreckoning.framework.entity.Entity;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.TexturedModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private final DefaultShaderPipeline shaderPipeline = new DefaultShaderPipeline();
    private final Renderer renderer = new Renderer(shaderPipeline);
    private final Map<TexturedModel, List<Entity>> entities = new HashMap<>();

    public void render(DirectionalLight directionalLight, Camera camera) {
        shaderPipeline.bind();
        shaderPipeline.loadDirectionalLight(directionalLight);
        shaderPipeline.loadViewMatrix(camera);

        renderer.render(entities);
        shaderPipeline.unbind();
        entities.clear();
    }

    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        List<Entity> batch = entities.get(model);

        if(batch != null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(model, newBatch);
        }
    }

    public void cleanup() {
        shaderPipeline.cleanup();
    }
}
