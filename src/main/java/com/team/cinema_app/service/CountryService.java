package com.team.cinema_app.service;

import com.team.cinema_app.dto.CountryRequest;
import com.team.cinema_app.dto.CountryResponse;
import com.team.cinema_app.exception.CountryNotFoundException;
import com.team.cinema_app.mapper.CountryMapper;
import com.team.cinema_app.model.Country;
import com.team.cinema_app.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CountryResponse getCountryById(UUID id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Жанр не найден c id " + id));
        return countryMapper.toResponse(country);
    }

    public CountryResponse createCountry(CountryRequest request){
        Country country = countryMapper.toEntity(request);
        countryRepository.save(country);

        return countryMapper.toResponse(country);
    }

    public void deleteCountry(UUID id){
        countryRepository.deleteById(id);
    }
}
