package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.entity.Entity;
import com.vgames.survivalreckoning.framework.entity.component.Transform;
import com.vgames.survivalreckoning.framework.log.*;
import com.vgames.survivalreckoning.framework.log.annotation.*;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.input.Input;
import com.vgames.survivalreckoning.framework.service.input.KeyCode;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.TextureLoader;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.*;

import java.util.Random;


@LogInfo(level = LogLevel.INFO)
@LogAlias("Survival Reckoning")
public class Game extends Logger {

    RawModel model;
    Material material;
    Entity entity;
    TexturedModel texturedModel;
    Texture texture;

    public void start() {
        info("Initializing now.");
        initializeStartScene();
    }

    private void initializeStartScene() {

        float[] vertices = {
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,

                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,

                -0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, 0.5f,

                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2,
                4, 5, 7,
                7, 5, 6,
                8, 9, 11,
                11, 9, 10,
                12, 13, 15,
                15, 13, 14,
                16, 17, 19,
                19, 17, 18,
                20, 21, 23,
                23, 21, 22
        };

        model = Engine.fromService(GraphicsAPI.class).loadModel("tree_2");
        texture = TextureLoader.getTexture("tree_2");
        material = new Material(texture, 10, 1, true);
        texturedModel = new TexturedModel(model, material);

        Random random = new Random();

        for (int i = 0; i < 2000; i++) {
            entity = new Entity(texturedModel,
                    new Transform(
                            new Vector3(random.nextInt(-10, 10),
                                    -1, random.nextInt(-10, 10)),
                            Vector3.zero(),
                            new Vector3(0.002f, 0.002f, 0.002f)
                    )
            );

            Engine.fromService(GraphicsAPI.class).pushRenderingObject(entity);
        }
    }

    public void update() {

        if (Input.isKeyPressed(KeyCode.SR_KEY_RIGHT)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.x += 0.003f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_LEFT)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.x -= 0.003f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_UP)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.z += 0.003f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_DOWN)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.z -= 0.003f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_D)) {
            material.reflectivity += 0.03f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_A)) {
            material.reflectivity -= 0.03f;
        }
    }
}
