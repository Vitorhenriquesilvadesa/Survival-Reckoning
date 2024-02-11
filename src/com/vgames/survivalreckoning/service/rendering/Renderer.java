package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.service.rendering.element.model.RawModel;

import static org.lwjgl.opengl.GL45.*;

public class Renderer {

    public void render(RawModel model) {
        glBindVertexArray(model.getVaoID());
        glEnableVertexAttribArray(0);
        glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
