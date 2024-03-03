package com.vgames.survivalreckoning.framework.engine.setting;

import com.vgames.survivalreckoning.framework.application.Game;

public class GearSettings {

    private int maxTicksPerSecond;
    private boolean useColdAnnotations;
    private String rootDirectory;
    private Class<? extends Game> gameClass;

    public GearSettings(int maxTicksPerSecond, boolean useColdAnnotations, String rootDirectory, Class<? extends Game> gameClass) {
        this.maxTicksPerSecond = maxTicksPerSecond;
        this.useColdAnnotations = useColdAnnotations;
        this.rootDirectory = rootDirectory;
        this.gameClass = gameClass;
    }

    public Integer getMaxTicksPerSecond() {
        return maxTicksPerSecond;
    }

    public boolean isUseColdAnnotations() {
        return useColdAnnotations;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public Class<? extends Game> getGameClass() {
        return gameClass;
    }
}
