package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.SeatTypeRequest;
import com.team.cinema_app.dto.SeatTypeResponse;
import com.team.cinema_app.model.SeatType;
import org.springframework.stereotype.Component;

@Component
public class SeatTypeMapper {

    public SeatTypeResponse toResponse(SeatType seatType){
        SeatTypeResponse response = new SeatTypeResponse();
        response.setId(seatType.getId());
        response.setTitle(seatType.getTitle());
        response.setPriceMultiplier(seatType.getPriceMultiplier());

        return response;
    }

    public SeatType toEntity(SeatTypeRequest request){
        return SeatType.builder()
                .title(request.getTitle())
                .priceMultiplier(request.getPriceMultiplier())
                .build();
    }
}
