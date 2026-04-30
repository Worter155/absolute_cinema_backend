package com.team.cinema_app.exception;

public class FilmCompanyNotFoundException extends RuntimeException {
    public FilmCompanyNotFoundException(String message) {
        super(message);
    }
}
