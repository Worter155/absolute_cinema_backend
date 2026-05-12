package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Schema(description = "Ответ с бронью")
@Data
@Builder
public class ReservationResponse {

    @Schema(description = "Id брони")
    private UUID reservationId;

    @Schema(description = "Id сеанса")
    private UUID sessionId;

    @Schema(description = "Название фильма")
    private String movieTitle;

    @Schema(description = "Название зала")
    private String hallTitle;

    @Schema(description = "Общая стоимость")
    private double totalPrice;

    @Schema(description = "Места")
    private List<SeatResponse> seats;
}
