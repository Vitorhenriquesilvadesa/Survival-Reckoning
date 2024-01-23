package com.vgames.survivalreckoning.entity.component;

import com.vgames.survivalreckoning.entity.Entity;
import com.vgames.survivalreckoning.log.annotation.LogAlias;
import com.vgames.survivalreckoning.util.math.Vector3;

@LogAlias(alias = "Transform")
public class TransformComponent extends Component {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public TransformComponent(Entity parent) {
        super(parent);
    }

    @Override
    public void update() {

    }
}
