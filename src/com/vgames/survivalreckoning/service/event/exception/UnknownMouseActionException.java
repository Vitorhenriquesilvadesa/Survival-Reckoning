package com.vgames.survivalreckoning.service.event.exception;

public class UnknownMouseActionException extends RuntimeException {
    public UnknownMouseActionException(int action) {
        super("Unknown mouse action: " + action);
    }
}
