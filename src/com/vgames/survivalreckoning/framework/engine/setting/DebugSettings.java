package com.vgames.survivalreckoning.framework.engine.setting;

public class DebugSettings {
    private boolean showLogs;
    private boolean generateCriticalFiles;

    public DebugSettings(boolean showLogs, boolean generateCriticalFiles) {
        this.showLogs = showLogs;
        this.generateCriticalFiles = generateCriticalFiles;
    }

    public Boolean isShowLogs() {
        return showLogs;
    }

    public Boolean isGenerateCriticalFiles() {
        return generateCriticalFiles;
    }
}
