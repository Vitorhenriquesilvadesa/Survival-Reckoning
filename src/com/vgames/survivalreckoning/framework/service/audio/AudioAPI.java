package com.vgames.survivalreckoning.framework.service.audio;

import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.log.LogLevel;
import com.vgames.survivalreckoning.framework.log.Logger;
import com.vgames.survivalreckoning.framework.log.annotation.LogInfo;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL;

@LogInfo(level = LogLevel.INFO)
public class AudioAPI extends Logger implements ApplicationService {

    boolean isPlayingInitAudio = false;

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public void update() {
        if (!isPlayingInitAudio) {
            play();
        }
    }

    public void play() {
    }

    @Override
    public void shutdown() {

    }
}
