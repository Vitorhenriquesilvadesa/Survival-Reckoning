package com.vgames.survivalreckoning.framework.service.rendering.renderer;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.SpriteRenderer;
import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.log.annotation.LogAlias;
import com.vgames.survivalreckoning.framework.service.rendering.element.light.DirectionalLight;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Model;
import com.vgames.survivalreckoning.framework.service.rendering.element.terrain.Terrain;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Camera;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Frustum;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.OrthographicFrustum;
import com.vgames.survivalreckoning.framework.service.rendering.shaderpipeline.EntityShaderPipeline;
import com.vgames.survivalreckoning.framework.service.rendering.shaderpipeline.TerrainShaderPipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LogAlias("Renderer")
public class MasterRenderer extends Logger {

    private final EntityShaderPipeline entityShaderPipeline = new EntityShaderPipeline();
    private final TerrainShaderPipeline terrainShaderPipeline = new TerrainShaderPipeline();
    private final EntityRenderer entityRenderer;
    private final TerrainRenderer terrainRenderer;
    private final Map<Model, List<GameObject>> entities = new HashMap<>();
    private final List<Terrain> terrains = new ArrayList<>();
    private Frustum frustum;
    private final int screenSize = 8;

    public MasterRenderer() {
        //this.frustum = new PerspectiveFrustum(70f, 0.1f, 1000f);
        this.frustum = new OrthographicFrustum(-screenSize, screenSize, -screenSize, screenSize, -screenSize, 100);
        this.entityRenderer = new EntityRenderer(entityShaderPipeline, frustum);
        this.terrainRenderer = new TerrainRenderer(terrainShaderPipeline, frustum);
    }


    public void render(DirectionalLight directionalLight, Camera camera) {
        entityShaderPipeline.bind();
        entityShaderPipeline.loadDirectionalLight(directionalLight);
        entityShaderPipeline.loadViewMatrix(camera);
        entityRenderer.render(entities);
        entityShaderPipeline.unbind();

        terrainShaderPipeline.bind();
        terrainShaderPipeline.loadDirectionalLight(directionalLight);
        terrainShaderPipeline.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShaderPipeline.unbind();

        terrains.clear();
        entities.clear();
    }

    public void processTerrain(Terrain terrain) {
        terrains.add(terrain);
    }

    public void processEntity(GameObject gameObject) {
        Model model = gameObject.getComponent(SpriteRenderer.class).getModel();

        if(model == null) return;

        List<GameObject> batch = entities.get(model);

        if (batch != null) {
            batch.add(gameObject);
        } else {
            List<GameObject> newBatch = new ArrayList<>();
            newBatch.add(gameObject);
            entities.put(model, newBatch);
        }
    }

    public void cleanup() {
        entityShaderPipeline.cleanup();
        terrainShaderPipeline.cleanup();
    }
}
