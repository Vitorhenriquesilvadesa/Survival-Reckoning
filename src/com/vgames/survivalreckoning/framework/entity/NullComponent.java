package com.vgames.survivalreckoning.framework.entity;

import com.vgames.survivalreckoning.framework.entity.component.Component;

public class NullComponent extends Component {

    private static final NullComponent instance = new NullComponent(new NullGameObject());
    private NullComponent(GameObject parent) {
        super(parent);
    }

    @Override
    public void dispose() {

    }

    public static Component get() {
        return instance;
    }
}
