package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.SeatRequest;
import com.team.cinema_app.dto.SeatResponse;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.Seat;
import com.team.cinema_app.model.SeatType;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public SeatResponse toResponse(Seat seat) {
        return SeatResponse.builder()
                .id(seat.getId())
                .hallTitle(seat.getHall().getTitle())
                .seatTypeTitle(seat.getSeatType().getTitle())
                .seatRow(seat.getSeatRow())
                .seatColumn(seat.getSeatColumn())
                .build();
    }

    public Seat toEntity(SeatRequest request, Hall hall, SeatType seatType) {
        return Seat.builder()
                .hall(hall)
                .seatType(seatType)
                .seatRow(request.getSeatRow())
                .seatColumn(request.getSeatColumn())
                .build();
    }

    public void updateEntity(Seat seat, SeatRequest request, Hall hall, SeatType seatType) {
        seat.setHall(hall);
        seat.setSeatType(seatType);
        seat.setSeatRow(request.getSeatRow());
        seat.setSeatColumn(request.getSeatColumn());
    }
}
