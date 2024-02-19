package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.Sprite.SpriteSheet;
import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.engine.Time;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.camera.CameraComponent;
import com.vgames.survivalreckoning.framework.entity.component.spriterenderer.SpriteRenderer;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.actions.KeyPressedEvent;
import com.vgames.survivalreckoning.framework.service.event.actions.WindowResizeEvent;
import com.vgames.survivalreckoning.framework.service.event.reactive.Reactive;
import com.vgames.survivalreckoning.framework.service.input.Input;
import com.vgames.survivalreckoning.framework.service.input.KeyCode;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ImageFilter;
import com.vgames.survivalreckoning.framework.service.rendering.element.material.Texture;

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

    @Override
    public void start() {
        Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        Engine.fromService(EventAPI.class).subscribe(this);

        Texture texture = Engine.fromService(GraphicsAPI.class).loadTexture("Temple", ImageFilter.POINT);
        gameObject = instantiate(new Transform(), SpriteRenderer.class, Box2DMesh.class, CameraComponent.class);
        spriteSheet = Engine.fromService(GraphicsAPI.class).loadSpriteSheet("Coin", 240, 16, 0, 0, 0, 16);
        frameCount = spriteSheet.getSprites().size();
        gameObject.getComponent(SpriteRenderer.class).setTexture(spriteSheet.getSprites().getFirst().texture());

    }

    @Override
    public void update() {

        time += Time.deltaTime();

        if(time >= 0.05f) {
            System.out.println("FPS: " + Time.fps());
            time = 0f;

            if(i < frameCount - 1) {
                gameObject.getComponent(SpriteRenderer.class).setTexture(spriteSheet.getSprites().get(i).texture());
                i++;
            } else {
                i = 0;
            }
        }

        if(Input.isKeyPressed(KeyCode.SR_KEY_E)) {
            width += 16f / 100f;
            height += 9f / 100f;
            Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        }

        Engine.fromService(EventAPI.class).dispatchEvent(new KeyPressedEvent(20));
    }

    @Reactive
    public void onCollisionEnter(WindowResizeEvent event) {
        System.out.println(event);
    }
}
