package com.vgames.survivalreckoning.framework.service.general.factory;

import com.vgames.survivalreckoning.framework.service.audio.AudioAPI;
import com.vgames.survivalreckoning.framework.service.general.ApplicationService;
import com.vgames.survivalreckoning.framework.service.event.EventAPI;
import com.vgames.survivalreckoning.framework.service.input.InputAPI;
import com.vgames.survivalreckoning.framework.service.general.provider.ServiceType;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.general.exception.UnknownServiceException;

public class ApplicationServiceFactory {

    public ApplicationServiceFactory() {
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
