package com.vgames.survivalreckoning.framework.service.rendering;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.camera.CameraComponent;
import com.vgames.survivalreckoning.framework.math.Vector2;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.event.actions.EventType;
import com.vgames.survivalreckoning.framework.service.event.actions.WindowResizeEvent;
import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.EventListener;
import com.vgames.survivalreckoning.framework.service.event.actions.Event;
import com.vgames.survivalreckoning.framework.service.rendering.element.light.DirectionalLight;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ImageFilter;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ObjLoader;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.TextureLoader;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Mesh;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.MeshLoader;
import com.vgames.survivalreckoning.framework.service.rendering.element.terrain.Terrain;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Camera;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.MasterRenderer;
import com.vgames.survivalreckoning.framework.service.rendering.shaderpipeline.ShaderPipelineBuilder;

import java.util.ArrayList;
import java.util.List;

public class GraphicsAPI extends Logger implements ApplicationService, EventListener {

    private GraphicContext graphicsContext;
    private ShaderPipelineBuilder shaderPipelineBuilder;
    private MasterRenderer renderer;
    private MeshLoader meshLoader;
    private TextureLoader textureLoader;
    private ObjLoader objLoader;
    private Camera activeCamera;
    public DirectionalLight directionalLight;
    private List<GameObject> models;
    private List<Terrain> terrains;
    private Vector2 viewportSize;
    private Vector2 windowSize;

    @Override
    public boolean init() {
        this.graphicsContext = new GraphicContext();

        if (this.graphicsContext.coreProfileIsEnabled()) {
            info("OpenGL running in Core Profile.");
        }
        this.models = new ArrayList<>();
        this.terrains = new ArrayList<>();
        this.meshLoader = new MeshLoader();
        this.objLoader = new ObjLoader();
        this.textureLoader = new TextureLoader();
        this.shaderPipelineBuilder = new ShaderPipelineBuilder();
        this.renderer = new MasterRenderer();
        this.activeCamera = new Camera(new Vector3(0, 0, -10), Vector3.zero(), new Vector3(1, 1, 1));
        this.directionalLight = new DirectionalLight(new Vector3(0, 5, 0), new Vector3(1, 1, 1));
        this.graphicsContext.setCallbacks();
        this.windowSize = new Vector2(1280f, 720f);
        return true;
    }

    @Override
    public void update() {
        graphicsContext.render();
        for (GameObject model : models) {
            renderer.processEntity(model);
        }
        for(Terrain terrain : terrains) {
            renderer.processTerrain(terrain);
        }
        renderer.render(directionalLight, activeCamera);
        graphicsContext.update();
    }

    public void setViewportSize(float viewportWidth, float viewportHeight) {

        assert viewportWidth > 0f && viewportHeight > 0f : "Viewport size must be greater than zero.";
        this.renderer.setViewportSize(viewportWidth, viewportHeight);
        this.viewportSize = new Vector2(viewportWidth, viewportHeight);
    }

    public int loadShader(String file, int shaderType) {
        return this.shaderPipelineBuilder.loadShader(file, shaderType);
    }

    public Mesh loadModel(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
        return this.meshLoader.loadToVAO(positions, textureCoords, normals, indices);
    }

    public Mesh loadModel(String file) {
        return this.objLoader.loadModel("src/resources/models/" + file, this.meshLoader);
    }

    public Texture loadTexture(String path, ImageFilter filter) {
        return this.textureLoader.getTexture("src/resources/textures/" + path, filter);
    }

    public Terrain loadTerrain(Vector2 position, String texturePath, ImageFilter filter) {
        return new Terrain((int) position.x, (int) position.y, this.meshLoader,
                new Material(textureLoader.getTexture("src/resources/textures/" + texturePath, filter),
                        0, 0, true));
    }

    public Camera getActiveCamera() {
        return activeCamera;
    }

    public void setActiveCamera(GameObject cameraParent) {
        this.activeCamera = cameraParent.getComponent(CameraComponent.class).getCamera();
    }

    public void setActiveCamera(Camera camera) {
        this.activeCamera = camera;
    }

    public void pushEntityInRenderingPool(GameObject model) {
        this.models.add(model);
    }

    public void pushTerrainInRenderingPool(Terrain terrain) {
        this.terrains.add(terrain);
    }

    @Override
    public void shutdown() {
        this.meshLoader.cleanup();
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
        if(e.getClass() == WindowResizeEvent.class) {
            this.windowSize.x = ((WindowResizeEvent) e).width;
            this.windowSize.y = ((WindowResizeEvent) e).height;
        }
        Engine.fromService(EventAPI.class).dispatchEvent(e);
    }

    public Vector2 getViewportSize() {
        return this.viewportSize;
    }

    public Vector2 getWindowSize() {
        return windowSize;
    }
}
