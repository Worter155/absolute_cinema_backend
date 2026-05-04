package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Ответ с типом зала")
@Data
public class HallTypeResponse {
    @Schema(description = "id")
    private UUID id;

    @Schema(description = "Название")
    private String title;

    @Schema(description = "Множитель цены")
    private double priceMultiplier;
}
