package com.vgames.survivalreckoning.service.rendering.element.model;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL45.*;

public class RawModelLoader {
    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private static boolean isCreated = false;

    public RawModelLoader() {
        if(!isCreated) {
            isCreated = true;
        } else {
            throw new IllegalStateException("Must one RawModelLoader can created!");
        }
    }

    public RawModel loadToVAO(float[] positions, int[] indices) {
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
    }

    public int createVAO() {
        int vaoID = glCreateVertexArrays();
        vaos.add(vaoID);
        glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vboID = glCreateBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeNumber, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

    }

    public void cleanup() {
        for(int vao : vaos) {
            glDeleteVertexArrays(vao);
        }

        for(int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
    }

    private void unbindVAO() {
        glBindVertexArray(0);
    }

    public void bindIndicesBuffer(int[] indices) {
        int iboID = glCreateBuffers();
        vbos.add(iboID);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    public IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
