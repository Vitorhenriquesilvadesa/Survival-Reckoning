package com.vgames.survivalreckoning.service.rendering.pipeline;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;

public class FragmentShader extends Shader {


    public FragmentShader(String sourceCode) {
        super(sourceCode, GL_FRAGMENT_SHADER);
        compile();
    }
}
