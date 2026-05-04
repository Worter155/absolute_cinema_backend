package com.team.cinema_app.controller;

import com.team.cinema_app.dto.GenreRequest;
import com.team.cinema_app.dto.GenreResponse;
import com.team.cinema_app.dto.MovieRequest;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Жанры",
        description = "Все методы для работы с жанрами"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/genre")
public class GenreController {

    private final GenreService genreService;

    @Operation(summary = "Получить все жанры")
    @GetMapping()
    public ResponseEntity<List<GenreResponse>> getAllGenres(){
        return ResponseEntity.ok().body(genreService.getAllGenres());
    }

    @Operation(summary = "Получить жанр по id")
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getGenreById(@PathVariable UUID id){
        return ResponseEntity.ok().body(genreService.getGenreById(id));
    }

    @Operation(summary = "Создать жанр")
    @PostMapping()
    public ResponseEntity<GenreResponse> createGenre(@Valid @RequestBody GenreRequest request){
        return ResponseEntity.ok().body(genreService.createGenre(request));
    }

    @Operation(summary = "Изменить жанр по id")
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> updateGenreById(@PathVariable UUID id, @Valid @RequestBody GenreRequest request) {
        return ResponseEntity.ok().body(genreService.updateGenreById(id, request));
    }

    @Operation(summary = "Удалить жанр по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id){
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
