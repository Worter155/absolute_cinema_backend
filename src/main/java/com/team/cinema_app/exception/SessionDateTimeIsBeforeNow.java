package com.team.cinema_app.exception;

public class SessionDateTimeIsBeforeNow extends RuntimeException {
    public SessionDateTimeIsBeforeNow(String message) {
        super(message);
    }
}
