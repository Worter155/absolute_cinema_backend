package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Ответ со страной")
@Data
public class CountryResponse {
    @Schema(description = "id")
    private UUID id;

    @Schema(description = "Название")
    private String title;
}
