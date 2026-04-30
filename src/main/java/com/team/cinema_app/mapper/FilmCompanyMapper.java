package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.FilmCompanyRequest;
import com.team.cinema_app.dto.FilmCompanyResponse;
import com.team.cinema_app.model.FilmCompany;
import org.springframework.stereotype.Component;

@Component
public class FilmCompanyMapper {
    public FilmCompanyResponse toResponse(FilmCompany filmCompany){
        FilmCompanyResponse filmCompanyResponse = new FilmCompanyResponse();
        filmCompanyResponse.setId(filmCompany.getId());
        filmCompanyResponse.setTitle(filmCompany.getTitle());

        return filmCompanyResponse;
    }

    public FilmCompany toEntity(FilmCompanyRequest request){
        return FilmCompany.builder()
                .title(request.getTitle())
                .build();
    }
}
