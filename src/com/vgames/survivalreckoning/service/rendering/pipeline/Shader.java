package com.vgames.survivalreckoning.service.rendering.pipeline;

import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;

import static org.lwjgl.opengl.GL45.*;

@LogInfo(level = LogLevel.ERROR)
public abstract class Shader extends Logger {

    protected int id;
    protected int typeIdentifier;
    protected String sourceCode;
    protected boolean success = true;

    public Shader(String sourceCode, int typeIdentifier) {
        this.sourceCode = sourceCode;
        this.typeIdentifier = typeIdentifier;
        createShader();
    }

    private void createShader() {
        this.id = glCreateShader(typeIdentifier);
    }

    protected void compile() {
        glShaderSource(this.id, this.sourceCode);
        glCompileShader(this.id);

        int shaderInfo = glGetShaderi(this.id, GL_COMPILE_STATUS);

        if(shaderInfo == GL_FALSE) {
            this.success = false;
            error("Compilation error: " + glGetShaderInfoLog(this.id));
        }
    }
}
