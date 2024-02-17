package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.SpriteRenderer;
import com.vgames.survivalreckoning.framework.entity.component.Transform;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DSize;
import com.vgames.survivalreckoning.framework.math.Vector2;
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

        texture = Engine.fromService(GraphicsAPI.class).loadTexture("Temple", ImageFilter.POINT);

        gameObject = instantiate(
                new Transform(new Vector3(0, 2, -1), new Vector3(0, 0, 0), 1f),
                SpriteRenderer.class, Box2DMesh.class);


        gameObject.getComponent(Box2DMesh.class).setProps(new Box2DSize(3, 3, new Vector2(0, 0)));

        System.out.println(gameObject.getComponent(Box2DMesh.class).getMesh());
        gameObject.getComponent(SpriteRenderer.class).setTexture(texture);

        Engine.fromService(GraphicsAPI.class).pushEntityInRenderingPool(gameObject);
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
