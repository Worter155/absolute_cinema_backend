package com.team.cinema_app.controller;

import com.team.cinema_app.dto.CountryRequest;
import com.team.cinema_app.dto.CountryResponse;
import com.team.cinema_app.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Страны",
        description = "Все методы для работы со странами"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    @Operation(summary = "Получить все страны")
    @GetMapping()
    public ResponseEntity<List<CountryResponse>> getAllCountrys() {
        return ResponseEntity.ok().body(countryService.getAllCountries());
    }

    @Operation(summary = "Получить страну по id")
    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(countryService.getCountryById(id));
    }

    @Operation(summary = "Создать страну")
    @PostMapping()
    public ResponseEntity<CountryResponse> createCountry(@Valid @RequestBody CountryRequest request) {
        return ResponseEntity.ok().body(countryService.createCountry(request));
    }

    @Operation(summary = "Изменить страну по id")
    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> updateCountryById(@PathVariable UUID id, @Valid @RequestBody CountryRequest request) {
        return ResponseEntity.ok().body(countryService.updateCountryById(id, request));
    }

    @Operation(summary = "Удалить страну по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable UUID id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
