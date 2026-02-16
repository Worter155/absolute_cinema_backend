package com.team.cinema_app.controller;

import com.team.cinema_app.dto.MovieRequest;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.model.Movie;
import com.team.cinema_app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public MovieResponse createMovie(@RequestBody MovieRequest request){
        return movieService.createMovie(request);
    }

    @GetMapping
    public List<MovieResponse> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @PutMapping("/{id}")
    public MovieResponse updateMovieById(@PathVariable Long id, @RequestBody MovieRequest request){
        return movieService.updateMovieById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }

    @PostMapping("/{id}/poster")
    public ResponseEntity<?> uploadPoster(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        movieService.uploadPoster(id, file);
        return ResponseEntity.ok("Постер загружен");
    }

    @GetMapping("/{id}/poster")
    public ResponseEntity<Resource> getPoster(@PathVariable Long id) {

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
