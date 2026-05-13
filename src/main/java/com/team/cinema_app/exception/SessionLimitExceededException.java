package com.team.cinema_app.exception;

public class SessionLimitExceededException extends RuntimeException {
    public SessionLimitExceededException(String message) {
        super(message);
    }
}
