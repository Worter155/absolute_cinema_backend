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
        SeatResponse response = new SeatResponse();

        response.setId(seat.getId());
        response.setHallTitle(seat.getHall().getTitle());
        response.setSeatTypeTitle(seat.getSeatType().getTitle());
        response.setSeatRow(seat.getSeatRow());
        response.setSeatColumn(seat.getSeatColumn());

        return response;
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
