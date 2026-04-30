package com.team.cinema_app.controller;

import com.team.cinema_app.dto.CountryRequest;
import com.team.cinema_app.dto.CountryResponse;
import com.team.cinema_app.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping()
    public ResponseEntity<List<CountryResponse>> getAllCountrys() {
        return ResponseEntity.ok().body(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(countryService.getCountryById(id));
    }

    @PostMapping()
    public ResponseEntity<CountryResponse> createCountry(@Valid @RequestBody CountryRequest request) {
        return ResponseEntity.ok().body(countryService.createCountry(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> updateCountryById(@PathVariable UUID id, @Valid @RequestBody CountryRequest request) {
        return ResponseEntity.ok().body(countryService.updateCountryById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable UUID id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
