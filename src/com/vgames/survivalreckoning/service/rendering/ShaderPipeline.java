package com.vgames.survivalreckoning.service.rendering;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.log.Logger;

import static org.lwjgl.opengl.GL45.*;

public abstract class ShaderPipeline extends Logger {

    private final int programID;
    private final int vertexShaderID;
    private final int fragmentShaderID;

    public ShaderPipeline(String vertexFile, String fragmentFile) {
        this.vertexShaderID = Engine.fromService(GraphicsAPI.class).loadShader(vertexFile, GL_VERTEX_SHADER);
        this.fragmentShaderID = Engine.fromService(GraphicsAPI.class).loadShader(fragmentFile, GL_FRAGMENT_SHADER);
        this.programID = glCreateProgram();

        glAttachShader(this.programID, this.vertexShaderID);
        glAttachShader(this.programID, this.fragmentShaderID);
        glLinkProgram(this.programID);
        glValidateProgram(this.programID);
        bindAttributes();
    }

    protected abstract void bindAttributes();

    public void bind() {
        glUseProgram(this.programID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        glDetachShader(this.programID, this.vertexShaderID);
        glDetachShader(this.programID, this.fragmentShaderID);
        glDeleteShader(this.vertexShaderID);
        glDeleteShader(this.fragmentShaderID);
        glDeleteProgram(this.programID);
    }

    protected void bindAttribute(int attribute, String variableName) {
        glBindAttribLocation(this.programID, attribute, variableName);
    }
}
