package com.vgames.survivalreckoning.framework.service.rendering.renderer;

import com.vgames.survivalreckoning.framework.math.Mathf;
import com.vgames.survivalreckoning.framework.math.Matrix4f;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Mesh;
import com.vgames.survivalreckoning.framework.service.rendering.element.terrain.Terrain;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Frustum;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.PerspectiveFrustum;
import com.vgames.survivalreckoning.framework.service.rendering.shaderpipeline.TerrainShaderPipeline;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class TerrainRenderer {

    private Frustum frustum;
    private TerrainShaderPipeline shaderPipeline;

    public TerrainRenderer(@NotNull TerrainShaderPipeline shaderPipeline) {
        this.shaderPipeline = shaderPipeline;
        this.frustum = new PerspectiveFrustum(70f, 0.1f, 1000f);
        //this.frustum = new OrthographicFrustum(-1, 1, -1, 1, 0.1f, 1000f);
        shaderPipeline.bind();
        shaderPipeline.loadProjectionMatrix(frustum.getProjectionMatrix());
        shaderPipeline.unbind();
    }

    public TerrainRenderer(@NotNull TerrainShaderPipeline shaderPipeline, @NotNull Frustum frustum) {
        this.shaderPipeline = shaderPipeline;
        this.frustum = frustum;
        //this.frustum = new OrthographicFrustum(-1, 1, -1, 1, 0.1f, 1000f);
        shaderPipeline.bind();
        shaderPipeline.loadProjectionMatrix(frustum.getProjectionMatrix());
        shaderPipeline.unbind();
    }

    public void render(List<Terrain> terrains) {
        for(Terrain terrain : terrains) {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            glDrawElements(GL_TRIANGLES, terrain.getMesh().vertexCount(), GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }

    public void prepareTerrain(Terrain terrain) {
        Mesh mesh = terrain.getMesh();
        Material material = terrain.getMaterial();

        if (material.isDoubleSided) {
            glDisable(GL_CULL_FACE);
        } else {
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);
        }

        glEnable(GL_DEPTH_TEST);
        glBindVertexArray(mesh.vaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        shaderPipeline.loadMaterialVariables(material.smoothness, material.reflectivity);

        glActiveTexture(GL_TEXTURE0);
        material.getTexture().bind();
    }

    public void unbindTexturedModel() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    public void loadModelMatrix(Terrain terrain) {
        Matrix4f transformationMatrix = Mathf.createTransformationMatrix(terrain.transform);
        shaderPipeline.loadTransformationMatrix(transformationMatrix);
    }

    public void setFrustum(Frustum frustum) {
        this.frustum = frustum;
    }
}
