package com.team.cinema_app.exception;

public class CantSendEmailException extends RuntimeException {
    public CantSendEmailException(String message) {
        super(message);
    }
}
