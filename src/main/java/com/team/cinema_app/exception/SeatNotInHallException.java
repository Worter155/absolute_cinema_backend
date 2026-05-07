package com.team.cinema_app.exception;

public class SeatNotInHallException extends RuntimeException {
    public SeatNotInHallException(String message) {
        super(message);
    }
}
