package com.team.cinema_app.exception;

public class CantCreateQrCodeException extends RuntimeException {
    public CantCreateQrCodeException(String message) {
        super(message);
    }
}
