package com.vgames.survivalreckoning.framework.service.rendering.scene;

import java.util.UUID;

public class SceneUUID {

    private final UUID id;

    public SceneUUID() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
