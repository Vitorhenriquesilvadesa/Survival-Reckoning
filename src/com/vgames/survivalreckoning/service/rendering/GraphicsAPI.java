package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;
import com.vgames.survivalreckoning.service.event.EventAPI;
import com.vgames.survivalreckoning.service.event.EventListener;
import com.vgames.survivalreckoning.service.event.actions.Event;
import com.vgames.survivalreckoning.service.rendering.element.model.RawModel;
import com.vgames.survivalreckoning.service.rendering.element.model.RawModelLoader;

import java.util.ArrayList;
import java.util.List;

@LogInfo(level = LogLevel.INFO)
public class GraphicsAPI extends Logger implements ApplicationService, EventListener {

    private GraphicContext graphicsContext;
    private ShaderPipelineBuilder shaderPipelineBuilder;
    private ShaderPipeline shaderPipeline;
    private Renderer renderer;
    private RawModelLoader rawModelLoader;
    private List<RawModel> models;

    @Override
    public boolean init() {
        this.graphicsContext = new GraphicContext();
        this.renderer = new Renderer();
        this.models = new ArrayList<>();
        this.rawModelLoader = new RawModelLoader();
        this.shaderPipelineBuilder = new ShaderPipelineBuilder();
        this.shaderPipeline = new DefaultShaderPipeline();
        this.graphicsContext.setCallbacks();
        return true;
    }

    @Override
    public void update() {
        graphicsContext.render();
        shaderPipeline.bind();
        for(RawModel model : models) {
            renderer.render(model);
        }
        shaderPipeline.unbind();
        graphicsContext.update();
    }

    public int loadShader(String file, int shaderType) {
        return this.shaderPipelineBuilder.loadShader(file, shaderType);
    }

    public RawModel loadRawModel(float[] positions, int[] indices) {
        return this.rawModelLoader.loadToVAO(positions, indices);
    }

    public void pushRenderingObject(RawModel model) {
        this.models.add(model);
    }

    @Override
    public void shutdown() {
        this.rawModelLoader.cleanup();
        this.shaderPipeline.cleanup();
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
