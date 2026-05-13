package com.team.cinema_app.exception;

public class MaxGenresCountException extends RuntimeException {
    public MaxGenresCountException(String message) {
        super(message);
    }
}
