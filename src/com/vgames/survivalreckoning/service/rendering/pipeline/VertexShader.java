package com.vgames.survivalreckoning.service.rendering.pipeline;

import static org.lwjgl.opengl.GL45.GL_VERTEX_SHADER;

public class VertexShader extends Shader {

    public VertexShader(String sourceCode) {
        super(sourceCode, GL_VERTEX_SHADER);
        compile();
    }
}
