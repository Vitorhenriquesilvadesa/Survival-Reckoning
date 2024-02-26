package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.Sprite.SpriteSheet;
import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.design_patterns.injection.Inject;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.engine.Time;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.PlayerMovement;
import com.vgames.survivalreckoning.framework.entity.component.collider.BoxCollider2D;
import com.vgames.survivalreckoning.framework.entity.component.collider.CollisionHash;
import com.vgames.survivalreckoning.framework.entity.component.movement.TopDownMovement;
import com.vgames.survivalreckoning.framework.entity.component.spriterenderer.SpriteRenderer;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;
import com.vgames.survivalreckoning.framework.math.Vector3;
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
        Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        Engine.fromService(EventAPI.class).subscribe(this);
        Engine.fromService(EventAPI.class).resolveDependencies(this);

        Scene scene = new Scene("MainScene");
        scene.addLayer("test");
        scene.addLayer("test2");

        Engine.fromService(GraphicsAPI.class).pushSceneInCurrentStack(scene);

        GameObject gameObject2 = instantiate(new Transform(), SpriteRenderer.class, Box2DMesh.class, TopDownMovement.class, PlayerMovement.class);
        spriteSheet = Engine.fromService(GraphicsAPI.class).loadSpriteSheet("coin", 240, 16, 0, 0, 0, 16);
        frameCount = spriteSheet.getSprites().size();
        gameObject2.getComponent(SpriteRenderer.class).setTexture(spriteSheet.getSprites().getFirst().texture());

        Engine.fromService(GraphicsAPI.class).putObjectInScene(gameObject2, "test");
    }

    @Override
    public void update() {

        time += Time.deltaTime();

        if(time >= 1f) {
            System.out.println("FPS: " + Time.fps());
            time = 0f;
        }

        if(input.isKeyPressed(KeyCode.SR_KEY_LEFT)) {
            System.out.println("Pressionado");
        }
    }

    @Reactive
    public void onCollisionEnter(CollisionEvent event) {
    }
}
