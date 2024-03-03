package com.vgames.survivalreckoning.framework.engine;

import com.vgames.survivalreckoning.framework.engine.setting.SettingsClassBuilder;
import com.vgames.survivalreckoning.framework.engine.setting.SettingsFileReader;

public class EngineStarter {

    public EngineStarter(String configFilepath) {
        Engine engine = Engine.getInstance();
        SettingsClassBuilder settingsClassBuilder = SettingsFileReader.readFile(configFilepath);
        engine.setDebugSettings(settingsClassBuilder.buildDebugSettings());
        engine.setGearSettings(settingsClassBuilder.buildGearSettings());
        engine.setWindowSettings(settingsClassBuilder.buildWindowSettings());
    }
}
