package com.vgames.survivalreckoning.framework.entity.component.rotation;

import com.vgames.survivalreckoning.framework.engine.Time;
import com.vgames.survivalreckoning.framework.entity.GameObject;
import com.vgames.survivalreckoning.framework.entity.component.Component;
import com.vgames.survivalreckoning.framework.math.Vector3;

public class RotationComponent extends Component {

    public RotationComponent(GameObject parent) {
        super(parent);
    }

    @Override
    public void update() {
        Vector3 rotation = parent.transform.getRotation();
        float increment = 5f * Time.time();
        parent.transform.setRotation(new Vector3(rotation.x, rotation.y, increment));
    }
}
