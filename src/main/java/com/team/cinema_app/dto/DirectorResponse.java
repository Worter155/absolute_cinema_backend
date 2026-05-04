package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Ответ с режиссером")
@Data
public class DirectorResponse {
    @Schema(description = "id")
    private UUID id;
    @Schema(description = "Название")
    private String name;
}
