package com.team.cinema_app.controller;

import com.team.cinema_app.dto.HallTypeRequest;
import com.team.cinema_app.dto.HallTypeResponse;
import com.team.cinema_app.service.HallTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Типы залов",
        description = "Все методы для работы с типами залов"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hallTypes")
public class HallTypeController {
    private final HallTypeService hallTypeService;

    @Operation(summary = "Получить все типы залов")
    @GetMapping()
    public ResponseEntity<List<HallTypeResponse>> getAllHallTypes(){
        return ResponseEntity.ok().body(hallTypeService.getAllHallTypes());
    }

    @Operation(summary = "Получить тип зала по id")
    @GetMapping("/{id}")
    public ResponseEntity<HallTypeResponse> getHallTypeById(@PathVariable UUID id){
        return ResponseEntity.ok().body(hallTypeService.getHallTypeById(id));
    }

    @Operation(summary = "Создать тип зала")
    @PostMapping()
    public ResponseEntity<HallTypeResponse> createHallType(@Valid @RequestBody HallTypeRequest request){
        return ResponseEntity.ok().body(hallTypeService.createHallType(request));
    }

    @Operation(summary = "Изменить тип зала по id")
    @PutMapping("/{id}")
    public ResponseEntity<HallTypeResponse> updateHallType(@PathVariable UUID id, @Valid @RequestBody HallTypeRequest request){
        return ResponseEntity.ok().body(hallTypeService.updateHallTypeById(id, request));
    }

    @Operation(summary = "Удалить тип зала по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHallTypeById(@PathVariable UUID id){
        hallTypeService.deleteHallTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
