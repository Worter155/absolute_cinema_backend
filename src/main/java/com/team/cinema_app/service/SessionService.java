package com.team.cinema_app.service;

import com.team.cinema_app.dto.SessionRequest;
import com.team.cinema_app.dto.SessionResponse;
import com.team.cinema_app.exception.HallNotFoundException;
import com.team.cinema_app.exception.MovieNotFoundException;
import com.team.cinema_app.exception.SessionDateTimeIsBeforeNow;
import com.team.cinema_app.exception.SessionNotFoundException;
import com.team.cinema_app.mapper.SessionMapper;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.Movie;
import com.team.cinema_app.model.Session;
import com.team.cinema_app.repository.HallRepository;
import com.team.cinema_app.repository.MovieRepository;
import com.team.cinema_app.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final SessionMapper sessionMapper;

    public List<SessionResponse> getAllSessions(){
        return sessionRepository.findAll()
                .stream()
                .map(sessionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<SessionResponse> getAllSessionsByDate(LocalDate date){
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return sessionRepository.findAllByDateTimeBetween(start,end)
                .stream()
                .map(sessionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SessionResponse getSessionById(UUID id){
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Сеанс не найден c id " + id));

        return sessionMapper.toResponse(session);
    }

    public SessionResponse createSession(SessionRequest request){

        if(LocalDateTime.parse(request.getDateTime()).isBefore(LocalDateTime.now())){
            throw new SessionDateTimeIsBeforeNow("Дата и время сеанса не могут быть в прошлом");
        }

        Movie movie = movieRepository.findById(UUID.fromString(request.getMovieId()))
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + request.getMovieId()));

        Hall hall = hallRepository.findById(UUID.fromString(request.getHallId()))
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + request.getHallId()));

        Session session = sessionRepository.save(sessionMapper.toEntity(request, movie, hall));

        return sessionMapper.toResponse(session);
    }

    public SessionResponse updateSessionById(UUID id, SessionRequest request){

        if(LocalDateTime.parse(request.getDateTime()).isBefore(LocalDateTime.now())){
            throw new SessionDateTimeIsBeforeNow("Дата и время сеанса не могут быть в прошлом");
        }

        Movie movie = movieRepository.findById(UUID.fromString(request.getMovieId()))
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + request.getMovieId()));

        Hall hall = hallRepository.findById(UUID.fromString(request.getHallId()))
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + request.getHallId()));

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Сеанс не найден c id " + id));

        sessionMapper.updateEntity(request, session, movie, hall);

        Session updated = sessionRepository.save(session);

        return sessionMapper.toResponse(updated);
    }

    public void deleteSessionById(UUID id){
        sessionRepository.deleteById(id);
    }
}
