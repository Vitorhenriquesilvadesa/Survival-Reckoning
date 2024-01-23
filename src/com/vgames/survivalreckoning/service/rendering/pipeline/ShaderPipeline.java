package com.vgames.survivalreckoning.service.rendering.pipeline;


import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;

import static org.lwjgl.opengl.GL45.*;

@LogInfo(level = LogLevel.ERROR)
public class ShaderPipeline extends Logger {

    private int shaderProgramId;
    private VertexShader vertexShader;
    private FragmentShader fragmentShader;
    boolean success = true;

    protected ShaderPipeline() {

    }

    public ShaderPipeline(VertexShader vertexShader, FragmentShader fragmentShader) {
        createProgram();
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
    }

    public ShaderPipeline(ShaderPipeline pipeline) {
        this.vertexShader = pipeline.vertexShader;
        this.fragmentShader = pipeline.fragmentShader;
        this.shaderProgramId = pipeline.shaderProgramId;
        this.success = pipeline.success;
    }

    public void bind() {
        glUseProgram(shaderProgramId);
    }

    public void attachShaders() {
        attachShader(ShaderType.VERTEX);
        attachShader(ShaderType.FRAGMENT);
    }

    private void attachShader(ShaderType type) {
        switch(type) {
            case VERTEX -> glAttachShader(this.shaderProgramId, this.vertexShader.id);
            case FRAGMENT -> glAttachShader(this.shaderProgramId, this.fragmentShader.id);
        }
    }

    public void linkAndValidate() {
        if(!(vertexShader.success || fragmentShader.success)) {
            this.success = false;
        }

        glLinkProgram(this.shaderProgramId);

        int linkStatus = glGetProgrami(this.shaderProgramId, GL_LINK_STATUS);

        if(linkStatus == GL_FALSE) {
            this.success = false;
            error("Shader program link error: " + glGetProgramInfoLog(this.shaderProgramId));
        }

        glValidateProgram(this.shaderProgramId);

        int validateStatus = glGetProgrami(this.shaderProgramId, GL_VALIDATE_STATUS);

        if(validateStatus == GL_FALSE) {
            this.success = false;
            error("Shader program validate error: " + glGetProgramInfoLog(this.shaderProgramId));
        }
    }

    public void createProgram() {
        this.shaderProgramId = glCreateProgram();
    }

    public void setFragmentShader(String source) {
        fragmentShader = new FragmentShader(source);
    }

    public void setVertexShader(String source) {
        vertexShader = new VertexShader(source);
    }
}