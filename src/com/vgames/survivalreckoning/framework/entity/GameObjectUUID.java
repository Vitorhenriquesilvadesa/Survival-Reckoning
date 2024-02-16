package com.vgames.survivalreckoning.framework.entity;

import java.util.UUID;

public class GameObjectUUID {

    private final UUID uuid;

    public GameObjectUUID() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
