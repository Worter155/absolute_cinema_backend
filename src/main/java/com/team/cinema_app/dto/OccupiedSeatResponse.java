package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Отсвет с занятым местом")
@Data
@Builder
public class OccupiedSeatResponse {

    @Schema(description = "id места")
    private UUID seatId;

    @Schema(description = "Номер ряда")
    private int row;

    @Schema(description = "Номер места в ряду")
    private int column;
}
