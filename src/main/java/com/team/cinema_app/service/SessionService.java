package com.team.cinema_app.service;

import com.team.cinema_app.dto.OccupiedSeatResponse;
import com.team.cinema_app.dto.SessionRequest;
import com.team.cinema_app.dto.SessionResponse;
import com.team.cinema_app.exception.*;
import com.team.cinema_app.mapper.SessionMapper;
import com.team.cinema_app.model.*;
import com.team.cinema_app.repository.*;
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

    private static final long MAX_SESSIONS_PER_DAY = 25;

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final SeatRepository seatRepository;
    private final SessionMapper sessionMapper;

    public List<SessionResponse> getAllSessions() {
        return sessionRepository.findAll()
                .stream()
                .map(sessionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<SessionResponse> getAllSessionsByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return sessionRepository.findAllByDateTimeBetween(start, end)
                .stream()
                .map(sessionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SessionResponse getSessionById(UUID id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Сеанс не найден c id " + id));

        return sessionMapper.toResponse(session);
    }

    public List<OccupiedSeatResponse> getOccupiedSeatsBySessionId(UUID sessionId) {

        List<ReservationSeat> reservationSeats = reservationSeatRepository.findAllBySessionId(sessionId);

        return reservationSeats.stream()
                .map(rs -> OccupiedSeatResponse.builder()
                        .seatId(rs.getSeat().getId())
                        .row(rs.getSeat().getSeatRow())
                        .column(rs.getSeat().getSeatColumn())
                        .build())
                .toList();
    }

    public double getSeatPrice(UUID sessionId, UUID seatId) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Сеанс не найден с id " + sessionId));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotFoundException("Место не найдено с id " + seatId));

        return Math.round(seat.getSeatType().getPriceMultiplier() * session.getBasePrice() * session.getHall().getHallType().getPriceMultiplier() * 100.0) / 100.0;

    }

    public SessionResponse createSession(SessionRequest request) {

        if (LocalDateTime.parse(request.getDateTime()).isBefore(LocalDateTime.now())) {
            throw new SessionDateTimeIsBeforeNow("Дата и время сеанса не могут быть в прошлом");
        }

        LocalDateTime sessionDateTime = LocalDateTime.parse(request.getDateTime());
        LocalDateTime startOfDay = sessionDateTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = sessionDateTime.toLocalDate().atTime(LocalTime.MAX);

        long sessionsCount = sessionRepository.countByDateTimeBetween(startOfDay, endOfDay);

        if (sessionsCount >= MAX_SESSIONS_PER_DAY) {
            throw new SessionLimitExceededException("На этот день уже создано максимальное количество сеансов (%s)".formatted(MAX_SESSIONS_PER_DAY));
        }

        Movie movie = movieRepository.findById(UUID.fromString(request.getMovieId()))
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + request.getMovieId()));

        Hall hall = hallRepository.findById(UUID.fromString(request.getHallId()))
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + request.getHallId()));

        Session session = sessionRepository.save(sessionMapper.toEntity(request, movie, hall));

        return sessionMapper.toResponse(session);
    }

    public SessionResponse updateSessionById(UUID id, SessionRequest request) {

        if (LocalDateTime.parse(request.getDateTime()).isBefore(LocalDateTime.now())) {
            throw new SessionDateTimeIsBeforeNow("Дата и время сеанса не могут быть в прошлом");
        }

        Movie movie = movieRepository.findById(UUID.fromString(request.getMovieId()))
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + request.getMovieId()));

        Hall hall = hallRepository.findById(UUID.fromString(request.getHallId()))
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + request.getHallId()));

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Сеанс не найден c id " + id));

        LocalDate newDate = LocalDateTime.parse(request.getDateTime()).toLocalDate();

        if (!session.getDateTime().toLocalDate().equals(newDate)) {

            LocalDateTime startOfDay = newDate.atStartOfDay();
            LocalDateTime endOfDay = newDate.atTime(LocalTime.MAX);

            long sessionsCount = sessionRepository.countByDateTimeBetween(startOfDay, endOfDay);

            if (sessionsCount >= MAX_SESSIONS_PER_DAY) {
                throw new SessionLimitExceededException(
                        "На этот день уже создано максимальное количество сеансов (%s)".formatted(MAX_SESSIONS_PER_DAY)
                );
            }
        }

        sessionMapper.updateEntity(request, session, movie, hall);

        Session updated = sessionRepository.save(session);

        return sessionMapper.toResponse(updated);
    }

    public void deleteSessionById(UUID id) {
        sessionRepository.deleteById(id);
    }
}
