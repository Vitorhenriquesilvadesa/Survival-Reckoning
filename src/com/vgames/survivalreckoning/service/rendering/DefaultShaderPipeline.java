package com.vgames.survivalreckoning.service.rendering;

public class DefaultShaderPipeline extends ShaderPipeline {

    private static final String VERTEX_FILE = "src/resources/shaders/default.vert";
    private static final String FRAGMENT_FILE = "src/resources/shaders/default.frag";
    public DefaultShaderPipeline() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
