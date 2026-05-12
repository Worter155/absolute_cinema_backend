package com.team.cinema_app.exception;

public class CantSaveFileException extends RuntimeException {
    public CantSaveFileException(String message) {
        super(message);
    }
}
