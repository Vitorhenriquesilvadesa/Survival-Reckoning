package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.animatior.Animator;
import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.Engine;
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

import java.security.Key;

import static com.vgames.survivalreckoning.framework.service.pool.ObjectPoolAPI.*;

public class SurvivalReckoning extends Game {
    GameObject gameObject;
    float width = 320;
    float height = 180;
    @Override
    public void start() {
        Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        Engine.fromService(EventAPI.class).subscribe(this);

        gameObject = instantiate(new Transform(), SpriteRenderer.class, Box2DMesh.class, CameraComponent.class, Animator.class);
//        gameObject.getComponent(Animator.class).addAnimation(Engine.fromService(GraphicsAPI.class).loadAnimation("ch",4,32,2f, 0), "right");

        gameObject.getComponent(Animator.class).addAnimation(Engine.fromService(GraphicsAPI.class).loadAnimation("ch",5,32,2f,0), "right_walking");
    }
    @Override
   public void update() {
        if(Input.isKeyPressed(KeyCode.SR_KEY_E)) {
            width += 16f / 100f;
            height += 9f / 100f;
            Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        }
        if(Input.isKeyPressed(KeyCode.SR_KEY_W)){
            gameObject.getComponent(Animator.class).playAnimation("walking");
        }   if(Input.isKeyPressed(KeyCode.SR_KEY_A)){
            gameObject.getComponent(Animator.class).playAnimation("walking");
        }   if(Input.isKeyPressed(KeyCode.SR_KEY_S)){
            gameObject.getComponent(Animator.class).playAnimation("right_walking");
        }
        if(Input.isKeyPressed(KeyCode.SR_KEY_D)){
            gameObject.getComponent(Animator.class).playAnimation("right");
        }
        Engine.fromService(EventAPI.class).dispatchEvent(new KeyPressedEvent(20));
    }

    @Reactive
    public void onCollisionEnter(WindowResizeEvent event) {
        System.out.println(event);
    }
}
