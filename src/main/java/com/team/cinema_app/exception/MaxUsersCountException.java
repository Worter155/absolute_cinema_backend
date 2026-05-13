package com.team.cinema_app.exception;

public class MaxUsersCountException extends RuntimeException {
    public MaxUsersCountException(String message) {
        super(message);
    }
}
