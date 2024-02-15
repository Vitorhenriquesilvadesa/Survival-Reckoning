package com.vgames.survivalreckoning.framework.entity;

import java.util.UUID;

public class GameUUID {

    private final UUID uuid;

    public GameUUID() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
