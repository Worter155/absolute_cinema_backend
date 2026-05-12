package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Schema(description = "Ответ с типом зала")
@Data
public class HallResponse {
    @Schema(description = "id")
    private UUID id;

    @Schema(description = "Название зала")
    private String title;

    @Schema(description = "Название типа зала")
    private String hallTypeTitle;

    @Schema(description = "Количество рядов")
    private int rows;

    @Schema(description = "Количество мест в ряду")
    private int columns;

    @Schema(description = "Список мест в зале")
    private List<SeatResponse> seats;
}
