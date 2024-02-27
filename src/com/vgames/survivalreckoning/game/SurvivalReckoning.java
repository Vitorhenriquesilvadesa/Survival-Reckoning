package com.vgames.survivalreckoning.game;

import com.vgames.survivalreckoning.framework.animatior.Animator;
import com.vgames.survivalreckoning.framework.application.Game;
import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.camera.CameraComponent;
import com.vgames.survivalreckoning.framework.entity.component.spriterenderer.SpriteRenderer;
import com.vgames.survivalreckoning.framework.entity.Transform;
import com.vgames.survivalreckoning.framework.entity.component.box2dmesh.Box2DMesh;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.event.actions.KeyPressedEvent;
import com.vgames.survivalreckoning.framework.service.event.actions.WindowResizeEvent;
import com.vgames.survivalreckoning.framework.service.event.reactive.Reactive;
import com.vgames.survivalreckoning.framework.service.input.Input;
import com.vgames.survivalreckoning.framework.service.input.KeyCode;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;

import java.util.ArrayList;

import static com.vgames.survivalreckoning.framework.service.pool.ObjectPoolAPI.*;

public class SurvivalReckoning extends Game {
    GameObject gameObject;
    GameObject tree;
    float width = 320;
    float height = 180;
    final float VELOCITY = 0.05f;
    @Override
    public void start() {
        Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        Engine.fromService(EventAPI.class).subscribe(this);
        gameObject = instantiate(new Transform(), SpriteRenderer.class, Box2DMesh.class, CameraComponent.class, Animator.class);

        ArrayList<Float> frameDuration = new ArrayList<>();
        frameDuration.add(0.5f);
        frameDuration.add(0.5f);
        frameDuration.add(0.5f);
        frameDuration.add(0.5f);
        ArrayList<Float> frameDuration2 = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            frameDuration2.add(0.5f);
        }

        gameObject.getComponent(Animator.class).addAnimation(Engine.fromService(GraphicsAPI.class).loadAnimation("tiles", 2,0,16,1,frameDuration2,1,1), "front_walking",5);

//        gameObject.getComponent(Animator.class).addAnimation(Engine.fromService(GraphicsAPI.class).loadAnimation("character", 0,frameDuration2,24,1), "front_walking",1);
    }
    @Override
   public void update() {
        if(Input.isKeyPressed(KeyCode.SR_KEY_E)) {
            width += 16f / 100f;
            height += 9f / 100f;
            Engine.fromService(GraphicsAPI.class).setViewportSize(width, height);
        }
        Vector3 position = gameObject.transform.getPosition();
//        if(Input.isKeyPressed(KeyCode.SR_KEY_W)){
//            gameObject.getComponent(Animator.class).playAnimation("front_walking");
//            position.y -= VELOCITY;
//            gameObject.transform.setPosition(position);
//        }else
//       if(Input.isKeyPressed(KeyCode.SR_KEY_A)){
//            gameObject.getComponent(Animator.class).playAnimation("right_walking");
//            gameObject.transform.setRotation(new Vector3(0,0,0));
//            position.x -= VELOCITY;
//            gameObject.transform.setPosition(position);
//        }else
//       if(Input.isKeyPressed(KeyCode.SR_KEY_S)){
//            gameObject.getComponent(Animator.class).playAnimation("back_walking");
//            position.y += VELOCITY;
//            gameObject.transform.setPosition(position);
//        }else
//       if(Input.isKeyPressed(KeyCode.SR_KEY_D)){
//            gameObject.getComponent(Animator.class).playAnimation("right_walking");
//           gameObject.transform.setRotation(new Vector3(0,0,0));
//           position.x += VELOCITY;
//            gameObject.transform.setPosition(position);
//        }else{
//           gameObject.getComponent(Animator.class).puaseAnimation();
//       }
        Engine.fromService(EventAPI.class).dispatchEvent(new KeyPressedEvent(20));
    }

    @Reactive
    public void onCollisionEnter(WindowResizeEvent event) {
        System.out.println(event);
    }
}
