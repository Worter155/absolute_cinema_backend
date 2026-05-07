package com.team.cinema_app.controller;

import com.team.cinema_app.dto.SeatTypeRequest;
import com.team.cinema_app.dto.SeatTypeResponse;
import com.team.cinema_app.service.SeatTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(
        name = "Типы мест",
        description = "Все методы для работы с типами мест"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seatTypes")
public class SeatTypeController {
    private final SeatTypeService seatTypeService;

    @Operation(summary = "Получить все типы мест")
    @GetMapping()
    public ResponseEntity<List<SeatTypeResponse>> getAllSeatTypes(){
        return ResponseEntity.ok().body(seatTypeService.getAllSeatTypes());
    }

    @Operation(summary = "Получить тип места по id")
    @GetMapping("/{id}")
    public ResponseEntity<SeatTypeResponse> getSeatTypeById(@PathVariable UUID id){
        return ResponseEntity.ok().body(seatTypeService.getSeatTypeById(id));
    }

    @Operation(summary = "Создать тип места")
    @PostMapping()
    public ResponseEntity<SeatTypeResponse> createSeatType(@Valid @RequestBody SeatTypeRequest request){
        return ResponseEntity.ok().body(seatTypeService.createSeatType(request));
    }

    @Operation(summary = "Изменить тип места по id")
    @PutMapping("/{id}")
    public ResponseEntity<SeatTypeResponse> updateSeatType(@PathVariable UUID id, @Valid @RequestBody SeatTypeRequest request){
        return ResponseEntity.ok().body(seatTypeService.updateSeatTypeById(id, request));
    }

    @Operation(summary = "Удалить тип места по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeatTypeById(@PathVariable UUID id){
        seatTypeService.deleteSeatTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
