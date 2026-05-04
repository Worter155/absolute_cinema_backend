package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.HallRequest;
import com.team.cinema_app.dto.HallResponse;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.HallType;
import org.springframework.stereotype.Component;

@Component
public class HallMapper {

    public HallResponse toResponse(Hall hall){
        HallResponse response = new HallResponse();
        response.setId(hall.getId());
        response.setTitle(hall.getTitle());
        response.setHallTypeTitle(hall.getHallType().getTitle());
        response.setRows(hall.getRows());
        response.setColumns(hall.getColumns());

        return response;
    }

    public Hall toEntity(HallRequest request, HallType hallType){
        return Hall.builder()
                .title(request.getTitle())
                .hallType(hallType)
                .rows(request.getRows())
                .columns(request.getColumns())
                .build();
    }

    public void updateEntity(Hall hall,
                             HallRequest request,
                             HallType hallType){
        hall.setTitle(request.getTitle());
        hall.setHallType(hallType);
        hall.setRows(request.getRows());
        hall.setColumns(request.getColumns());
    }
}
