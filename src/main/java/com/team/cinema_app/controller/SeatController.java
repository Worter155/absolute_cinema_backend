package com.team.cinema_app.controller;

import com.team.cinema_app.dto.SeatRequest;
import com.team.cinema_app.dto.SeatResponse;
import com.team.cinema_app.service.SeatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Места",
        description = "Все методы для работы с местами"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/hall/{hallId}")
    public ResponseEntity<List<SeatResponse>> getAllSeatsByHallId(@PathVariable UUID hallId){
        return ResponseEntity.ok().body(seatService.getAllSeatsByHallId(hallId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeatById(@PathVariable UUID id){
        return ResponseEntity.ok().body(seatService.getSeatById(id));
    }

    @PostMapping()
    public ResponseEntity<SeatResponse> createSeat(@Valid @RequestBody SeatRequest request){
        return ResponseEntity.ok().body(seatService.createSeat(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatResponse> updateSeatById(@PathVariable UUID id, @Valid @RequestBody SeatRequest request){
        return ResponseEntity.ok().body(seatService.updateSeatById(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeatById(@PathVariable UUID id){
        seatService.deleteSeatById(id);
        return ResponseEntity.noContent().build();
    }
}
