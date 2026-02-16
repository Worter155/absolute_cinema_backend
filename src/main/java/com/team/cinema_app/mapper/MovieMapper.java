package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.MovieRequest;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.model.*;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie toEntity(MovieRequest request,
                                 Genre genre,
                                 Country country,
                                 Director director,
                                 FilmCompany filmCompany){
        return Movie.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .ageLimit(request.getAgeLimit())
                .genre(genre)
                .country(country)
                .director(director)
                .filmCompany(filmCompany)
                .build();
    }

    public void updateEntity(Movie movie,
                             MovieRequest request,
                             Genre genre,
                             Country country,
                             Director director,
                             FilmCompany filmCompany) {

        movie.setTitle(request.getTitle());
        movie.setDuration(request.getDuration());
        movie.setAgeLimit(request.getAgeLimit());
        movie.setGenre(genre);
        movie.setCountry(country);
        movie.setDirector(director);
        movie.setFilmCompany(filmCompany);
    }

    public MovieResponse toResponse(Movie movie){
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setDuration(movie.getDuration());
        response.setAgeLimit(movie.getAgeLimit());
        response.setGenreTitle(movie.getGenre().getTitle());
        response.setCountryTitle(movie.getCountry().getTitle());
        response.setDirectorName(movie.getDirector().getName());
        response.setFilmCompanyTitle(movie.getFilmCompany().getTitle());

        if (movie.getPosterPath() != null) {
            response.setPosterUrl("/api/movies/" + movie.getId() + "/poster");
        }

        return response;
    }
}
