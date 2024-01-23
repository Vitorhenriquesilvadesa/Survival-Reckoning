package com.vgames.survivalreckoning.service.rendering.pipeline;

import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogAlias;
import com.vgames.survivalreckoning.log.annotation.LogInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@LogInfo(level = LogLevel.INFO)
public class ShaderPipelineBuilder extends Logger {

    ShaderPipeline pipeline;

    public ShaderPipelineBuilder() {
        this.pipeline = new ShaderPipeline();
    }

    public ShaderPipelineBuilder initPipeline() {
        pipeline.createProgram();
        return this;
    }

    public ShaderPipelineBuilder setFragmentShader(String path) {
        pipeline.setFragmentShader(parseShader(path));
        return this;
    }

    public ShaderPipelineBuilder setVertexShader(String path) {
        pipeline.setVertexShader(parseShader(path));
        return this;
    }

    private String parseShader(String path) {

        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return shaderSource.toString();
    }

    public ShaderPipeline build() {
        ShaderPipeline builtPipeline = new ShaderPipeline(pipeline);
        builtPipeline.attachShaders();
        builtPipeline.linkAndValidate();

        if(!builtPipeline.success) {
            error("May contain linking, validation, or compilation errors.");
        } else {
            info("Successful to compile shaders, link and validate pipeline.");
        }
        return new ShaderPipeline(builtPipeline);
    }
}
