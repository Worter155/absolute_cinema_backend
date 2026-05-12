package com.team.cinema_app.exception;

public class CantDeleteFileException extends RuntimeException {
    public CantDeleteFileException(String message) {
        super(message);
    }
}
