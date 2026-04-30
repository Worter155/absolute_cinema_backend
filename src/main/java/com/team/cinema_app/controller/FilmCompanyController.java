package com.team.cinema_app.controller;

import com.team.cinema_app.dto.FilmCompanyRequest;
import com.team.cinema_app.dto.FilmCompanyResponse;
import com.team.cinema_app.service.FilmCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/filmCompany")
public class FilmCompanyController {
    private final FilmCompanyService filmCompanyService;

    @GetMapping()
    public ResponseEntity<List<FilmCompanyResponse>> getAllFilmCompanies(){
        return ResponseEntity.ok().body(filmCompanyService.getAllFilmCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmCompanyResponse> getFilmCompanyById(@PathVariable UUID id){
        return ResponseEntity.ok().body(filmCompanyService.getFilmCompanyById(id));
    }

    @PostMapping()
    public ResponseEntity<FilmCompanyResponse> createFilmCompany(@Valid @RequestBody FilmCompanyRequest request){
        return ResponseEntity.ok().body(filmCompanyService.createFilmCompany(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmCompany(@PathVariable UUID id){
        filmCompanyService.deleteFilmCompany(id);
        return ResponseEntity.noContent().build();
    }
}
