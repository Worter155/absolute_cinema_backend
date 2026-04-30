package com.team.cinema_app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleMovieNotFoundException(MovieNotFoundException ex){
        log.warn("Movie not found {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Movie not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleGenreNotFoundException(GenreNotFoundException ex){
        log.warn("Genre not found {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Genre not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCountryNotFoundException(CountryNotFoundException ex){
        log.warn("Country not found {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Country not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DirectorNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleDirectorNotFoundException(DirectorNotFoundException ex){
        log.warn("Director not found {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Director not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(FilmCompanyNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleFilmCompanyNotFoundException(FilmCompanyNotFoundException ex){
        log.warn("Film Company not found {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Film Company not found");
        return ResponseEntity.badRequest().body(errors);
    }
}
