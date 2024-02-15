package com.vgames.survivalreckoning.framework.entity.component;

import com.vgames.survivalreckoning.framework.entity.GameObject;

public class RotationComponent extends Component {

    protected RotationComponent(GameObject parent) {
        super(parent);
    }

    @Override
    public void update() {
        parent.transform.rotation.z += 0.005f;
    }
}
