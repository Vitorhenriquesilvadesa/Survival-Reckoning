package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.design_patterns.injection.Inject;
import com.vgames.survivalreckoning.framework.engine.Time;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.movement.TopDownMovement;
import com.vgames.survivalreckoning.framework.math.Vector2;
import com.vgames.survivalreckoning.framework.service.input.InputManager;
import com.vgames.survivalreckoning.framework.service.input.KeyCode;

public class PlayerMovement extends Component{

    @Inject
    private InputManager input;
    private TopDownMovement topDownMovement;
    private final float speed = 32f;

    public PlayerMovement(GameObject parent) {
        super(parent);
    }

    public void start() {
        topDownMovement = getComponent(TopDownMovement.class);
    }

    public void update() {
        Vector2 movement = Vector2.zero();

        if(input.isKeyPressed(KeyCode.SR_KEY_UP)) {
            movement.y += 1;
        }

        if(input.isKeyPressed(KeyCode.SR_KEY_DOWN)) {
            movement.y -= 1;
        }

        if(input.isKeyPressed(KeyCode.SR_KEY_LEFT)) {
            movement.x -= 1;
        }

        if(input.isKeyPressed(KeyCode.SR_KEY_RIGHT)) {
            movement.x += 1;
        }

        movement = Vector2.normalize(movement);
        movement = Vector2.multiply(movement, speed);

        topDownMovement.move(Vector2.multiply(movement, Time.deltaTime()));
    }
}
