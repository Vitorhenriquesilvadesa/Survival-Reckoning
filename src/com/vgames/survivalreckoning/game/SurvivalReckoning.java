package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.RotationComponent;
import com.vgames.survivalreckoning.framework.entity.component.SpriteRenderer;
import com.vgames.survivalreckoning.framework.entity.component.Transform;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.input.Input;
import com.vgames.survivalreckoning.framework.service.input.KeyCode;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ImageFilter;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Material;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Mesh;
import com.vgames.survivalreckoning.framework.service.rendering.element.model.Model;

import static com.vgames.survivalreckoning.framework.service.pool.ObjectPoolAPI.*;

public class SurvivalReckoning extends Game {
    Mesh mesh;
    Material material;
    GameObject gameObject;
    Model model;
    Texture texture;

    @Override
    public void start() {
        info("Initializing now.");
        initializeStartScene();
    }

    private void initializeStartScene() {

        mesh = Engine.fromService(GraphicsAPI.class).loadModel("plane");
        texture = Engine.fromService(GraphicsAPI.class).loadTexture("Temple", ImageFilter.POINT);
        material = new Material(texture, 0, 0, true, true);
        model = new Model(mesh, material);

        //Engine.fromService(GraphicsAPI.class).pushTerrainInRenderingPool(terrain);

        gameObject = instantiate(
                new Transform(new Vector3(0, 2, -1), new Vector3(0, 0, 0), 1f),
                SpriteRenderer.class, RotationComponent.class);

        gameObject.getComponent(SpriteRenderer.class).setModel(model);

        Engine.fromService(GraphicsAPI.class).pushEntityInRenderingPool(gameObject);

    }

    @Override
    public void update() {

        if (Input.isKeyPressed(KeyCode.SR_KEY_RIGHT)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.x += 0.006f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_LEFT)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.x -= 0.006f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_UP)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.z += 0.006f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_DOWN)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.z -= 0.006f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_W)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.y += 0.006f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_S)) {
            Engine.fromService(GraphicsAPI.class).getCamera().transform.position.y -= 0.006f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_D)) {
            material.reflectivity += 0.03f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_A)) {
            material.reflectivity -= 0.03f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_SPACE)) {
            gameObject.transform.rotation.z += 0.002f;
        }
    }
}
