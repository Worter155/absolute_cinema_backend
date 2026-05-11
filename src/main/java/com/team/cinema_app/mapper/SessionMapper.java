package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.SessionRequest;
import com.team.cinema_app.dto.SessionResponse;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.Movie;
import com.team.cinema_app.model.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionMapper {

    public SessionResponse toResponse(Session session){
        SessionResponse response = new SessionResponse();

        response.setId(session.getId());
        response.setMovieTitle(session.getMovie().getTitle());
        response.setHallTitle(session.getHall().getTitle());
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
