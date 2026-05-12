package com.team.cinema_app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
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
    public ResponseEntity<Map<String, String>> handleMovieNotFoundException(MovieNotFoundException ex) {
        log.warn("Фильм не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Фильм не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGenreNotFoundException(GenreNotFoundException ex) {
        log.warn("Жанр не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Жанр не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCountryNotFoundException(CountryNotFoundException ex) {
        log.warn("Страна не найдена {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Страна не найдена");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DirectorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDirectorNotFoundException(DirectorNotFoundException ex) {
        log.warn("Режиссер не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Режиссер не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(FilmCompanyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleFilmCompanyNotFoundException(FilmCompanyNotFoundException ex) {
        log.warn("Кинокомпания не найдена {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Кинокомпания не найдена");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HallTypeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleHallTypeNotFoundException(HallTypeNotFoundException ex) {
        log.warn("Тип зала не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Тип зала не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HallNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleHallNotFoundException(HallNotFoundException ex) {
        log.warn("Зал не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Зал не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SeatTypeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSeatTypeNotFoundException(SeatTypeNotFoundException ex) {
        log.warn("Тип места не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Тип места не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSeatNotFoundException(SeatNotFoundException ex) {
        log.warn("Место не найдено {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Место не найдено");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SeatNotInHallException.class)
    public ResponseEntity<Map<String, String>> handleSeatNotInHallException(SeatNotInHallException ex) {
        log.warn("Место не может выходить за пределы зала {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Место не может выходить за пределы зала");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SeatAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleSeatAlreadyExistsException(SeatAlreadyExistsException ex) {
        log.warn("Такое место уже существует {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Такое место уже существует");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSessionNotFoundException(SessionNotFoundException ex) {
        log.warn("Сеанс не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Сеанс не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SessionDateTimeIsBeforeNow.class)
    public ResponseEntity<Map<String, String>> handleSessionDateTimeIsBeforeNow(SessionDateTimeIsBeforeNow ex) {
        log.warn("Дата и время сеанса не могут быть в прошлом {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Дата и время сеанса не могут быть в прошлом");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> handleDateTimeParseException(DateTimeParseException ex) {
        log.warn("Некорректная дата {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Некорректная дата");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyTakenException(EmailAlreadyTakenException ex) {
        log.warn("Электронная почта уже занята {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Электронная почта уже занята");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.warn("Электронная почта не найдена {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Электронная почта не найдена");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("Пользователь не найден {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Пользователь не найден");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleReservationNotFoundException(ReservationNotFoundException ex) {
        log.warn("Бронь не найдена {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Бронь не найдена");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<Map<String, String>> handleSeatAlreadyReservedException(SeatAlreadyReservedException ex) {
        log.warn("Место уже забронировано {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Место уже забронировано");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CantUploadFileException.class)
    public ResponseEntity<Map<String, String>> handleCantUploadFileException(CantUploadFileException ex) {
        log.warn("Не удалось загрузить файл {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Не удалось загрузить файл");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CantSaveFileException.class)
    public ResponseEntity<Map<String, String>> handleCantSaveFileException(CantSaveFileException ex) {
        log.warn("Не удалось сохранить файл {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Не удалось сохранить файл");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CantDeleteFileException.class)
    public ResponseEntity<Map<String, String>> handleCantDeleteFileException(CantDeleteFileException ex) {
        log.warn("Не удалось удалить файл {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Не удалось удалить файл");
        return ResponseEntity.badRequest().body(errors);
    }
}
