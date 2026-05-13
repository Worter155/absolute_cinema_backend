package com.team.cinema_app.exception;

public class MaxDirectorsCountException extends RuntimeException {
    public MaxDirectorsCountException(String message) {
        super(message);
    }
}
