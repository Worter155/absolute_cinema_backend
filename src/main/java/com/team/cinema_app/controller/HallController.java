package com.team.cinema_app.controller;

import com.team.cinema_app.dto.HallRequest;
import com.team.cinema_app.dto.HallResponse;
import com.team.cinema_app.service.HallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Залы",
        description = "Все методы для работы с залами"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/halls")
public class HallController {

    private final HallService hallService;

    @Operation(summary = "Получить все залы")
    @GetMapping()
    public ResponseEntity<List<HallResponse>> getAllHalls(){
        return ResponseEntity.ok().body(hallService.getAllHalls());
    }

    @Operation(summary = "Получить зал по id")
    @GetMapping("/{id}")
    public ResponseEntity<HallResponse> getHallById(@PathVariable UUID id){
        return ResponseEntity.ok().body(hallService.getHallById(id));
    }

    @Operation(summary = "Создать зал")
    @PostMapping()
    public ResponseEntity<HallResponse> createHall(@Valid @RequestBody HallRequest request){
        return ResponseEntity.ok().body(hallService.createHall(request));
    }

    @Operation(summary = "Изменить зал по id")
    @PutMapping("/{id}")
    public ResponseEntity<HallResponse> updateHall(@PathVariable UUID id, @Valid @RequestBody HallRequest request){
        return ResponseEntity.ok().body(hallService.updateHall(id, request));
    }

    @Operation(summary = "Удалить зал по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHallById(@PathVariable UUID id){
        hallService.deleteHallById(id);
        return ResponseEntity.noContent().build();
    }
}
