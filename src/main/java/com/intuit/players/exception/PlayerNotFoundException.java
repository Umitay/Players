package com.intuit.players.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String msg) {
        super(msg);
    }
}

