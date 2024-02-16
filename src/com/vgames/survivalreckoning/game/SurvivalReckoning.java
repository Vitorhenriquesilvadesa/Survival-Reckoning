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
        texture = Engine.fromService(GraphicsAPI.class).loadTexture("lp_100", ImageFilter.POINT);
        material = new Material(texture, 0, 0, true, true);

        //Engine.fromService(GraphicsAPI.class).pushTerrainInRenderingPool(terrain);

        gameObject = instantiate(
                new Transform(new Vector3(0, 2, -1), new Vector3(0, 0, 0), 1f),
                SpriteRenderer.class);

        GameObject entity = instantiate(
                new Transform(new Vector3(3, -2, -1), new Vector3(0, 0, 0), 1f, gameObject.transform),
                SpriteRenderer.class);

        gameObject.getComponent(SpriteRenderer.class).setModel(model);

        material = new Material(texture, 0, 0, true, true);
        model = new Model(mesh, material);

        entity.getComponent(SpriteRenderer.class).setModel(model);

        Engine.fromService(GraphicsAPI.class).pushEntityInRenderingPool(gameObject);
        Engine.fromService(GraphicsAPI.class).pushEntityInRenderingPool(entity);
    }

    @Override
    public void update() {

        Vector3 cameraPosition = Engine.fromService(GraphicsAPI.class).getCamera().transform.getPosition();

        if (Input.isKeyPressed(KeyCode.SR_KEY_RIGHT)) {
            cameraPosition.x += 0.005f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_LEFT)) {
            cameraPosition.x -= 0.005f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_UP)) {
            cameraPosition.z += 0.005f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_DOWN)) {
            cameraPosition.z -= 0.005f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_W)) {
            cameraPosition.y += 0.005f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_S)) {
            cameraPosition.y -= 0.005f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_D)) {
            material.reflectivity += 0.03f;
        }

        if (Input.isKeyPressed(KeyCode.SR_KEY_A)) {
            material.reflectivity -= 0.03f;
        }

        Vector3 position = gameObject.transform.getPosition();
        gameObject.transform.setPosition(new Vector3(position.x + 0.001f, position.y, position.z));
    }
}
