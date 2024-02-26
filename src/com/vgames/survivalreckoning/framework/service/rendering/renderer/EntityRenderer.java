package com.vgames.survivalreckoning.framework.service.rendering.renderer;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.math.Mathf;
import com.vgames.survivalreckoning.framework.math.Matrix4f;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Mesh;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Model;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.Frustum;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.OrthographicFrustum;
import com.vgames.survivalreckoning.framework.service.rendering.renderer.config.PerspectiveFrustum;
import com.vgames.survivalreckoning.framework.service.rendering.shaderpipeline.EntityShaderPipeline;

import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL45.*;

public class EntityRenderer {

    private Frustum frustum;
    private final EntityShaderPipeline shaderPipeline;

    public EntityRenderer(EntityShaderPipeline shaderPipeline) {
        this.shaderPipeline = shaderPipeline;
        //this.frustum = new PerspectiveFrustum(70f, 0.1f, 1000f);
        //this.frustum = new OrthographicFrustum(-16, 16, -9f, 9f, 0.1f, 1000f);
        shaderPipeline.bind();
        shaderPipeline.loadProjectionMatrix(frustum.getProjectionMatrix());
        shaderPipeline.unbind();
    }

    public EntityRenderer(EntityShaderPipeline shaderPipeline, Frustum frustum) {
        this.shaderPipeline = shaderPipeline;
        this.frustum = frustum;
        shaderPipeline.bind();
        shaderPipeline.loadProjectionMatrix(frustum.getProjectionMatrix());
        shaderPipeline.unbind();
    }

    public void render(Map<Model, List<GameObject>> entities, long entityCount) {

        final float space = 1 / (float) (entityCount + 1);

        for (Model model : entities.keySet()) {
            float offSet = space;

            prepareTexturedModel(model);
            List<GameObject> batch = entities.get(model);

            for (GameObject gameObject : batch) {
                prepareInstance(gameObject, offSet);
                offSet += space;
                glDrawElements(GL_TRIANGLES, model.getRawModel().vertexCount(), GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }

    public void prepareTexturedModel(Model model) {
        Mesh mesh = model.getRawModel();
        Material material = model.getMaterial();

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

        shaderPipeline.loadMaterialVariables(material);
        shaderPipeline.loadFakeLightingVariable(material.fakeLighting);

        glActiveTexture(GL_TEXTURE0);
        material.getTexture().bind();
    }

    public void unbindTexturedModel() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    public void prepareInstance(GameObject gameObject, float offset) {
        Transform transform = gameObject.transform;
        Vector3 position = transform.getPosition();
        Vector3 virtualPosition = Vector3.add(position, new Vector3(0, 0, offset));

        Matrix4f transformationMatrix = Mathf.createTransformationMatrix(virtualPosition, transform.getRotation(), transform.getScale());
        shaderPipeline.loadTransformationMatrix(transformationMatrix);
    }

    public void setFrustum(Frustum frustum) {
        this.frustum = frustum;
        shaderPipeline.bind();
        shaderPipeline.loadProjectionMatrix(frustum.getProjectionMatrix());
        shaderPipeline.unbind();
    }

    public Frustum getFrustum() {
        return this.frustum;
    }
}
