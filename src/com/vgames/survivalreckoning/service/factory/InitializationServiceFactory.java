package com.vgames.survivalreckoning.service.factory;

import com.vgames.survivalreckoning.service.audio.AudioAPI;
import com.vgames.survivalreckoning.service.general.ApplicationService;
import com.vgames.survivalreckoning.service.event.EventAPI;
import com.vgames.survivalreckoning.service.input.InputAPI;
import com.vgames.survivalreckoning.service.provider.ServiceType;
import com.vgames.survivalreckoning.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.service.exception.UnknownServiceException;

public class InitializationServiceFactory {

    public InitializationServiceFactory() {
    }

    public ApplicationService createService(ServiceType type) {
        return switch (type) {
            case GRAPHICS_API -> new GraphicsAPI();
            case AUDIO_API -> new AudioAPI();
            case EVENT_API -> new EventAPI();
            case INPUT_API -> new InputAPI();
            default -> throw new UnknownServiceException(type);
        };
    }
}
