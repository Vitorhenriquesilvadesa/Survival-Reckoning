package com.vgames.survivalreckoning.service.audio;

import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.log.LogLevel;
import com.vgames.survivalreckoning.log.Logger;
import com.vgames.survivalreckoning.log.annotation.LogInfo;

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
