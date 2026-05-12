package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.HallResponse;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.dto.SessionRequest;
import com.team.cinema_app.dto.SessionResponse;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.Movie;
import com.team.cinema_app.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SessionMapper {

    private final SeatMapper seatMapper;

    public SessionResponse toResponse(Session session){
        SessionResponse response = new SessionResponse();

        MovieResponse movie = new MovieResponse();

        movie.setId(session.getMovie().getId());
        movie.setTitle(session.getMovie().getTitle());
        movie.setDuration(session.getMovie().getDuration());
        movie.setAgeLimit(session.getMovie().getAgeLimit());
        movie.setCountryTitle(session.getMovie().getCountry().getTitle());
        movie.setDirectorName(session.getMovie().getDirector().getName());
        movie.setFilmCompanyTitle(session.getMovie().getFilmCompany().getTitle());
        movie.setGenreTitle(session.getMovie().getGenre().getTitle());
        if (session.getMovie().getPosterPath() != null) {
            movie.setPosterUrl("/api/movies/" + movie.getId() + "/poster");
        }

        HallResponse hall = new HallResponse();

        hall.setId(session.getHall().getId());
        hall.setTitle(session.getHall().getTitle());
        hall.setHallTypeTitle(session.getHall().getHallType().getTitle());
        hall.setRows(session.getHall().getRows());
        hall.setColumns(session.getHall().getColumns());
        hall.setSeats(session.getHall().getSeats().stream()
                .map(seatMapper::toResponse)
                .toList());

        response.setId(session.getId());
        response.setMovie(movie);
        response.setHall(hall);
        response.setDateTime(session.getDateTime().toString());
        response.setBasePrice(session.getBasePrice());

        return response;
    }

    public Session toEntity(SessionRequest request, Movie movie, Hall hall){
        return Session.builder()
                .movie(movie)
                .hall(hall)
                .dateTime(LocalDateTime.parse(request.getDateTime()))
                .basePrice(request.getBasePrice())
                .build();
    }

    public void updateEntity(SessionRequest request, Session session, Movie movie, Hall hall){
        session.setMovie(movie);
        session.setHall(hall);
        session.setDateTime(LocalDateTime.parse(request.getDateTime()));
        session.setBasePrice(request.getBasePrice());
    }
}
