package com.vgames.survivalreckoning.framework.service.rendering;

import com.vgames.survivalreckoning.framework.entity.Entity;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.EventListener;
import com.vgames.survivalreckoning.framework.service.event.actions.Event;
import com.vgames.survivalreckoning.framework.service.rendering.element.light.DirectionalLight;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ObjLoader;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.RawModel;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.RawModelLoader;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.Camera;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.MasterRenderer;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.ShaderPipelineBuilder;

import java.util.ArrayList;
import java.util.List;

public class GraphicsAPI extends Logger implements ApplicationService, EventListener {

    private GraphicContext graphicsContext;
    private ShaderPipelineBuilder shaderPipelineBuilder;
    private MasterRenderer renderer;
    private RawModelLoader rawModelLoader;
    private ObjLoader objLoader;
    private Camera camera;
    public DirectionalLight directionalLight;
    private List<Entity> models;

    @Override
    public boolean init() {
        this.graphicsContext = new GraphicContext();

        if (this.graphicsContext.coreProfileIsEnabled()) {
            info("OpenGL running in Core Profile.");
        }
        this.models = new ArrayList<>();
        this.rawModelLoader = new RawModelLoader();
        this.objLoader = new ObjLoader();
        this.shaderPipelineBuilder = new ShaderPipelineBuilder();
        this.renderer = new MasterRenderer();
        this.camera = new Camera(new Vector3(0, 0, 0), Vector3.zero(), new Vector3(1, 1, 1));
        this.directionalLight = new DirectionalLight(new Vector3(0, 0, 0), new Vector3(1, 1, 1));
        this.graphicsContext.setCallbacks();
        return true;
    }

    @Override
    public void update() {
        graphicsContext.render();
        for (Entity model : models) {
            renderer.processEntity(model);
        }
        renderer.render(directionalLight, camera);
        graphicsContext.update();
    }

    public int loadShader(String file, int shaderType) {
        return this.shaderPipelineBuilder.loadShader(file, shaderType);
    }

    public RawModel loadModel(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
        return this.rawModelLoader.loadToVAO(positions, textureCoords, normals, indices);
    }

    public RawModel loadModel(String file) {
        return this.objLoader.loadModel("src/resources/models/" + file, this.rawModelLoader);
    }

    public Camera getCamera() {
        return camera;
    }

    public void pushRenderingObject(Entity model) {
        this.models.add(model);
    }

    @Override
    public void shutdown() {
        this.rawModelLoader.cleanup();
        this.renderer.cleanup();
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
