package com.team.cinema_app.controller;

import com.team.cinema_app.dto.OccupiedSeatResponse;
import com.team.cinema_app.dto.SessionRequest;
import com.team.cinema_app.dto.SessionResponse;
import com.team.cinema_app.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Tag(
        name = "Сеансы",
        description = "Все методы для работы с сеансами"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Operation(summary = "Получить все сеансы")
    @GetMapping()
    public ResponseEntity<List<SessionResponse>> getAllSessions() {
        return ResponseEntity.ok().body(sessionService.getAllSessions());
    }

    @Operation(summary = "Получить все сеансы за конкретную дату")
    @GetMapping(params = "date")
    public ResponseEntity<List<SessionResponse>> getAllSessionsByDate(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return ResponseEntity.ok().body(sessionService.getAllSessionsByDate(date));
    }

    @Operation(summary = "Получить сеанс по id")
    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(sessionService.getSessionById(id));
    }

    @Operation(summary = "Получить все занятые места")
    @GetMapping("/occupied-seats/{sessionId}")
    public ResponseEntity<List<OccupiedSeatResponse>> getOccupiesSeatsBySessionId(@PathVariable UUID sessionId){
        return ResponseEntity.ok().body(sessionService.getOccupiedSeatsBySessionId(sessionId));
    }

    @Operation(summary = "Получить цену места в сеансе места")
    @GetMapping(value = "/seat-price")
    public ResponseEntity<Double> getSeatPrice(@RequestParam("sessionId") UUID sessionId, @RequestParam("seatId") UUID seatId){
        return ResponseEntity.ok().body(sessionService.getSeatPrice(sessionId,seatId));
    }

    @Operation(summary = "Создать сеанс")
    @PostMapping()
    public ResponseEntity<SessionResponse> createSession(@Valid @RequestBody SessionRequest request) {
        return ResponseEntity.ok().body(sessionService.createSession(request));
    }

    @Operation(summary = "Изменить сеанс по id")
    @PutMapping("/{id}")
    public ResponseEntity<SessionResponse> updateSessionById(@PathVariable UUID id, @Valid @RequestBody SessionRequest request) {
        return ResponseEntity.ok().body(sessionService.updateSessionById(id,request));
    }

    @Operation(summary = "Удалить сеанс по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessionById(@PathVariable UUID id){
        sessionService.deleteSessionById(id);
        return ResponseEntity.noContent().build();
    }
}
