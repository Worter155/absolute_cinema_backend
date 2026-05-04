package com.team.cinema_app.controller;

import com.team.cinema_app.dto.DirectorRequest;
import com.team.cinema_app.dto.DirectorResponse;
import com.team.cinema_app.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Режиссеры",
        description = "Все методы для работы с режиссерами"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/director")
public class DirectorController {
    private final DirectorService directorService;

    @Operation(summary = "Получить всех режиссеров")
    @GetMapping()
    public ResponseEntity<List<DirectorResponse>> getAllDirectors() {
        return ResponseEntity.ok().body(directorService.getAllDirectors());
    }

    @Operation(summary = "Получить режиссера по id")
    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponse> getDirectorById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(directorService.getDirectorById(id));
    }

    @Operation(summary = "Создать режиссера")
    @PostMapping()
    public ResponseEntity<DirectorResponse> createDirector(@Valid @RequestBody DirectorRequest request) {
        return ResponseEntity.ok().body(directorService.createDirector(request));
    }

    @Operation(summary = "Изменить режиссера по id")
    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponse> updateDirectorById(@PathVariable UUID id, @Valid @RequestBody DirectorRequest request) {
        return ResponseEntity.ok().body(directorService.updateDirectorById(id, request));
    }

    @Operation(summary = "Удалить режиссера по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable UUID id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}
