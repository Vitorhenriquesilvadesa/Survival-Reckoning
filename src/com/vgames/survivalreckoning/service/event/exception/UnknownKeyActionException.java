package com.vgames.survivalreckoning.service.event.exception;

public class UnknownKeyActionException extends RuntimeException {

    public UnknownKeyActionException(int action) {
        super("Unknown keyboard action: " + action);
    }
}
