package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.CountryRequest;
import com.team.cinema_app.dto.CountryResponse;
import com.team.cinema_app.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryResponse toResponse(Country country){
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setId(country.getId());
        countryResponse.setTitle(country.getTitle());

        return countryResponse;
    }

    public Country toEntity(CountryRequest request){
        return Country.builder()
                .title(request.getTitle())
                .build();
    }
}
