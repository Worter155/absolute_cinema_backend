package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.HallTypeRequest;
import com.team.cinema_app.dto.HallTypeResponse;
import com.team.cinema_app.model.HallType;
import org.springframework.stereotype.Component;

@Component
public class HallTypeMapper {

    public HallTypeResponse toResponse(HallType hallType){
        HallTypeResponse response = new HallTypeResponse();
        response.setId(hallType.getId());
        response.setTitle(hallType.getTitle());
        response.setPriceMultiplier(hallType.getPriceMultiplier());

        return response;
    }

    public HallType toEntity(HallTypeRequest request){
        return HallType.builder()
                .title(request.getTitle())
                .priceMultiplier(request.getPriceMultiplier())
                .build();
    }
}
