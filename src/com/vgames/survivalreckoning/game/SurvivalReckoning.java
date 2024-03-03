package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.Sprite.SpriteSheet;
import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.design_patterns.injection.Inject;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.engine.Time;
import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileReader;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.PlayerMovement;
import com.vgames.survivalreckoning.framework.entity.component.movement.TopDownMovement;
import com.vgames.survivalreckoning.framework.entity.component.spriterenderer.SpriteRenderer;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.actions.CollisionEvent;
import com.vgames.survivalreckoning.framework.service.event.reactive.Reactive;
import com.vgames.survivalreckoning.framework.service.input.InputManager;
import com.vgames.survivalreckoning.framework.service.input.KeyCode;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ImageFilter;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;
import com.vgames.survivalreckoning.framework.service.rendering.scene.Scene;

import static com.vgames.survivalreckoning.framework.service.pool.ObjectPoolAPI.*;

public class SurvivalReckoning extends Game {
    GameObject gameObject;
    float time = 0f;
    int i = 0;
    int frameCount;
    float width = 320;
    float height = 180;
    Texture animatedTexture;
    SpriteSheet spriteSheet;

    @Inject
    private InputManager input;

    @Override
    public void start() {
//        Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        Engine.fromService(EventAPI.class).subscribe(this);
        Engine.fromService(EventAPI.class).resolveDependencies(this);

        SettingsFileReader.readFile("src/resources/config/project.gconfig");

        Scene scene = new Scene("MainScene");
        scene.addLayer("test");

        Engine.fromService(GraphicsAPI.class).pushSceneInCurrentStack(scene);

        GameObject gameObject2 = instantiate(new Transform(), Box2DMesh.class, SpriteRenderer.class, TopDownMovement.class, PlayerMovement.class);
        animatedTexture = Engine.fromService(GraphicsAPI.class).loadTexture("Temple", ImageFilter.POINT);
        gameObject2.getComponent(SpriteRenderer.class).setTexture(animatedTexture);

        Engine.fromService(GraphicsAPI.class).putObjectInScene(gameObject2, "test");
    }

    @Override
    public void update() {

        time += Time.deltaTime();

        if(time >= 1f) {
            //System.out.println("FPS: " + Time.fps());
            time = 0f;
        }

        if(input.isKeyPressed(KeyCode.SR_KEY_K)) {
            System.out.println("zsognogsorosgsogosgosrrgrsgiurs");
        }
    }

    @Reactive
    public void onCollisionEnter(CollisionEvent event) {
    }
}
