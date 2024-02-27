package com.vgames.survivalreckoning.framework.entity.component.movement;

import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.math.Vector2;
import com.vgames.survivalreckoning.framework.math.Vector3;

public class TopDownMovement extends Component {

    public TopDownMovement(GameObject parent) {
        super(parent);
    }

    public void move(Vector2 delta) {
        delta = Vector2.normalize(delta);
        transform.setPosition(Vector3.add(new Vector3(delta.x, delta.y, 0f), transform.getPosition()));
    }
}