package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.GenreRequest;
import com.team.cinema_app.dto.GenreResponse;
import com.team.cinema_app.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponse toResponse(Genre genre) {
        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setId(genre.getId());
        genreResponse.setTitle(genre.getTitle());

        return genreResponse;
    }

    public Genre toEntity(GenreRequest request) {
        return Genre.builder()
                .title(request.getTitle())
                .build();
    }
}
