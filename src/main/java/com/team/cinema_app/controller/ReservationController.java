package com.team.cinema_app.controller;

import com.team.cinema_app.dto.OccupiedSeatResponse;
import com.team.cinema_app.dto.ReservationRequest;
import com.team.cinema_app.dto.ReservationResponse;
import com.team.cinema_app.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Брони",
        description = "Все методы для работы с бронированием"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "Получить все брони")
    @GetMapping()
    public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        return ResponseEntity.ok().body(reservationService.getAllReservations());
    }

    @Operation(summary = "Получить бронь по id")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable UUID id){
        return ResponseEntity.ok().body(reservationService.getReservationById(id));
    }

    @Operation(summary = "Получить все брони текущего пользователя")
    @GetMapping("/me")
    public ResponseEntity<List<ReservationResponse>> getAllReservationsForCurrentUser(Authentication authentication){
        return ResponseEntity.ok().body(reservationService.getAllReservationsForCurrentUser(authentication));
    }

    @Operation(summary = "Создать бронь")
    @PostMapping()
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest request, Authentication authentication){
        System.out.println("HIT CREATE RESERVATION");
        return ResponseEntity.ok().body(reservationService.createReservation(request, authentication));
    }

    @Operation(summary = "Удалить бронь по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable UUID id){
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удалить бронь текущего пользователя по id")
    @DeleteMapping("/me/{id}")
    public ResponseEntity<Void> deleteReservationByIdForCurrentUser(@PathVariable UUID id, Authentication authentication){
        reservationService.deleteReservationByIdForCurrentUser(id, authentication);
        return ResponseEntity.noContent().build();
    }
}
