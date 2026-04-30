package com.team.cinema_app.mapper;

import com.team.cinema_app.dto.DirectorRequest;
import com.team.cinema_app.dto.DirectorResponse;
import com.team.cinema_app.model.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {
    public DirectorResponse toResponse(Director director) {
        DirectorResponse directorResponse = new DirectorResponse();
        directorResponse.setId(director.getId());
        directorResponse.setName(director.getName());

        return directorResponse;
    }

    public Director toEntity(DirectorRequest request) {
        return Director.builder()
                .name(request.getName())
                .build();
    }
}
