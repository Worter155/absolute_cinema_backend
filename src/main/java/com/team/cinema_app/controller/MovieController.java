package com.team.cinema_app.controller;

import com.team.cinema_app.dto.MovieRequest;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.model.Movie;
import com.team.cinema_app.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Tag(
        name = "Фильмы",
        description = "Все методы для работы с фильмами"
)
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "Создать фильм")
    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        return ResponseEntity.ok().body(movieService.createMovie(request));
    }

    @Operation(summary = "Получить все фильмы")
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    @Operation(summary = "Получить фильм по id")
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @Operation(summary = "Изменить фильм по id")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovieById(@PathVariable UUID id, @Valid @RequestBody MovieRequest request) {
        return ResponseEntity.ok().body(movieService.updateMovieById(id, request));
    }

    @Operation(summary = "Удалить фильм по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Добавить постер у фильму по id")
    @PostMapping("/{id}/poster")
    public ResponseEntity<?> uploadPoster(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file) {

        movieService.uploadPoster(id, file);
        return ResponseEntity.ok("Постер загружен");
    }

    @Operation(summary = "Получить постер по id фильма")
    @GetMapping("/{id}/poster")
    public ResponseEntity<Resource> getPoster(@PathVariable UUID id) {

        Movie movie = movieService.findEntityById(id);

        if (movie.getPosterPath() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path path = Paths.get(movie.getPosterPath());
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить файл");
        }
    }


}
