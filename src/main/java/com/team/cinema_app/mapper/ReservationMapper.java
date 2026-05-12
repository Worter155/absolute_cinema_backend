package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.ReservationRequest;
import com.team.cinema_app.dto.ReservationResponse;
import com.team.cinema_app.dto.SeatResponse;
import com.team.cinema_app.model.Reservation;
import com.team.cinema_app.model.Session;
import com.team.cinema_app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    public ReservationResponse toResponse(Reservation reservation){
        List<SeatResponse> seats = reservation.getReservationSeats()
                .stream()
                .map(sr -> SeatResponse.builder()
                        .id(sr.getSeat().getId())
                        .hallTitle(sr.getSeat().getHall().getTitle())
                        .seatTypeTitle(sr.getSeat().getSeatType().getTitle())
                        .seatRow(sr.getSeat().getSeatRow())
                        .seatColumn(sr.getSeat().getSeatColumn())
                        .build())
                .toList();

        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .sessionId(reservation.getSession().getId())
                .movieTitle(
                        reservation.getSession()
                                .getMovie()
                                .getTitle()
                )
                .hallTitle(
                        reservation.getSession()
                                .getHall()
                                .getTitle()
                )
                .totalPrice(reservation.getTotalPrice())
                .seats(seats)
                .build();
    }

    public Reservation toEntity(User user, Session session){
        return Reservation.builder()
                .session(session)
                .user(user)
                .totalPrice(0)
                .build();
    }
}
