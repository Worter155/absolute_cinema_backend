package com.team.cinema_app.controller;

import com.team.cinema_app.dto.DirectorRequest;
import com.team.cinema_app.dto.DirectorResponse;
import com.team.cinema_app.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/director")
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping()
    public ResponseEntity<List<DirectorResponse>> getAllDirectors(){
        return ResponseEntity.ok().body(directorService.getAllDirectors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponse> getDirectorById(@PathVariable UUID id){
        return ResponseEntity.ok().body(directorService.getDirectorById(id));
    }

    @PostMapping()
    public ResponseEntity<DirectorResponse> createDirector(@Valid @RequestBody DirectorRequest request){
        return ResponseEntity.ok().body(directorService.createDirector(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable UUID id){
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}
