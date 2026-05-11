package com.team.cinema_app.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}
