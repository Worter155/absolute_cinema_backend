package com.team.cinema_app.controller;

import com.team.cinema_app.dto.FilmCompanyRequest;
import com.team.cinema_app.dto.FilmCompanyResponse;
import com.team.cinema_app.service.FilmCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Кинокомпании",
        description = "Все методы для работы с кинокомпаниями"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/filmCompany")
public class FilmCompanyController {
    private final FilmCompanyService filmCompanyService;

    @Operation(summary = "Получить все кинокомпании")
    @GetMapping()
    public ResponseEntity<List<FilmCompanyResponse>> getAllFilmCompanies() {
        return ResponseEntity.ok().body(filmCompanyService.getAllFilmCompanies());
    }

    @Operation(summary = "Получить кинокомпанию по id")
    @GetMapping("/{id}")
    public ResponseEntity<FilmCompanyResponse> getFilmCompanyById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(filmCompanyService.getFilmCompanyById(id));
    }

    @Operation(summary = "Создать кинокомпанию")
    @PostMapping()
    public ResponseEntity<FilmCompanyResponse> createFilmCompany(@Valid @RequestBody FilmCompanyRequest request) {
        return ResponseEntity.ok().body(filmCompanyService.createFilmCompany(request));
    }

    @Operation(summary = "Изменить кинокомпанию по id")
    @PutMapping("/{id}")
    public ResponseEntity<FilmCompanyResponse> updateFilmCompanyById(@PathVariable UUID id, @Valid @RequestBody FilmCompanyRequest request) {
        return ResponseEntity.ok().body(filmCompanyService.updateFilmCompanyById(id, request));
    }

    @Operation(summary = "Удалить кинокомпанию по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmCompany(@PathVariable UUID id) {
        filmCompanyService.deleteFilmCompany(id);
        return ResponseEntity.noContent().build();
    }
}
