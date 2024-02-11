package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.engine.Engine;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogAlias;
import com.vgames.survivalreckoning.log.annotation.LogInfo;
import com.vgames.survivalreckoning.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.service.rendering.element.model.RawModel;
import com.vgames.survivalreckoning.service.rendering.element.model.RawModelLoader;


@LogInfo(level = LogLevel.INFO)
@LogAlias("Survival Reckoning")
public class Game extends Logger {

    RawModel model;

    public void start() {
        info("Initializing now.");
        initializeStartScene();
    }

    private void initializeStartScene() {

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        model = Engine.fromService(GraphicsAPI.class).loadRawModel(vertices, indices);
        Engine.fromService(GraphicsAPI.class).pushRenderingObject(model);
    }

    public void update() {

    }
}
