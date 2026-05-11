package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Ответ с сеансом")
@Data
public class SessionResponse {
    @Schema(description = "id")
    private UUID id;

    @Schema(description = "Название фильма")
    private String movieTitle;

    @Schema(description = "Название зала")
    private String hallTitle;

    @Schema(description = "Дата и время сеанса")
    private String dateTime;

    @Schema(description = "Базовая цена")
    private double basePrice;
}
