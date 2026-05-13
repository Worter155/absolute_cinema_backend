package com.team.cinema_app.exception;

public class MaxFilmCompaniesCountException extends RuntimeException {
    public MaxFilmCompaniesCountException(String message) {
        super(message);
    }
}
