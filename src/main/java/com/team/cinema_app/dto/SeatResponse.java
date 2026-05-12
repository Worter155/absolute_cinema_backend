package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Ответ с местом")
@Data
@Builder
public class SeatResponse {
    @Schema(description = "id")
    private UUID id;

    @Schema(description = "Название зала")
    private String hallTitle;

    @Schema(description = "Название типа места")
    private String seatTypeTitle;

    @Schema(description = "Номер ряда")
    private int seatRow;

    @Schema(description = "Номер места в ряду")
    private int seatColumn;
}
