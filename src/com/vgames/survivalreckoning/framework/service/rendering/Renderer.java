package com.vgames.survivalreckoning.framework.service.rendering;

import com.vgames.survivalreckoning.framework.entity.Entity;
import com.vgames.survivalreckoning.framework.math.Mathf;
import com.vgames.survivalreckoning.framework.math.Matrix4f;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Material;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.RawModel;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.TexturedModel;

import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL45.*;

public class Renderer {

    private Frustum frustum;
    private DefaultShaderPipeline shaderPipeline;

    public Renderer(DefaultShaderPipeline shaderPipeline) {
        this.shaderPipeline = shaderPipeline;
        this.frustum = new PerspectiveFrustum(70f, 16f / 9f, 0.1f, 1000f);
        //this.frustum = new OrthographicFrustum(-1, 1, -1, 1, 0.1f, 1000f);
        shaderPipeline.bind();
        shaderPipeline.loadProjectionMatrix(frustum.getProjectionMatrix());
        shaderPipeline.unbind();
    }

    public void render(Map<TexturedModel, List<Entity>> entities) {
        for(TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);

            for(Entity entity : batch) {
                prepareInstance(entity);
                glDrawElements(GL_TRIANGLES, model.getRawModel().vertexCount(), GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }

    public void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        Material material = model.getMaterial();

        if (material.isDoubleSided) {
            glDisable(GL_CULL_FACE);
        } else {
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);
        }

        glEnable(GL_DEPTH_TEST);
        glBindVertexArray(rawModel.vaoID());
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

    public void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = Mathf.createTransformationMatrix(entity.transform);

        shaderPipeline.loadTransformationMatrix(transformationMatrix);
    }
}
