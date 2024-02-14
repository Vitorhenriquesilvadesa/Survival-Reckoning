package com.vgames.survivalreckoning.framework.entity;

import java.util.UUID;

public class EntityUUID {

    private final UUID uuid;

    public EntityUUID() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
