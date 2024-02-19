package com.vgames.survivalreckoning.framework.service.rendering.shaderpipeline;

import com.vgames.survivalreckoning.framework.log.annotation.LogAlias;
import com.vgames.survivalreckoning.framework.math.Mathf;
import com.vgames.survivalreckoning.framework.math.Matrix4f;
import com.vgames.survivalreckoning.framework.service.rendering.element.light.DirectionalLight;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Camera;

@LogAlias("Asset Loader")
public class EntityShaderPipeline extends ShaderPipeline {

    private static final String VERTEX_FILE = "src/resources/shaders/sprite.vert";
    private static final String FRAGMENT_FILE = "src/resources/shaders/sprite.frag";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColor;
    private int location_smoothness;
    private int location_reflectivity;
    private int location_fakeLighting;

    public EntityShaderPipeline() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = getUniformLocation("transformationMatrix");
        location_projectionMatrix = getUniformLocation("projectionMatrix");
        location_viewMatrix = getUniformLocation("viewMatrix");
        location_lightPosition = getUniformLocation("lightPosition");
        location_lightColor = getUniformLocation("lightColor");
        location_reflectivity = getUniformLocation("reflectivity");
        location_smoothness = getUniformLocation("smoothness");
        location_fakeLighting = getUniformLocation("useFakeLighting");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureSampler");
        super.bindAttribute(2, "normal");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Mathf.createViewMatrix(camera);
        loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadDirectionalLight(DirectionalLight light) {
        loadVector(location_lightPosition, light.position);
        loadVector(location_lightColor, light.color);
    }

    public void loadMaterialVariables(Material material) {
        loadFloat(location_smoothness, material.smoothness);
        loadFloat(location_reflectivity, material.reflectivity);
    }

    public void loadFakeLightingVariable(boolean useFake) {
        loadBoolean(location_fakeLighting, useFake);
    }
}
