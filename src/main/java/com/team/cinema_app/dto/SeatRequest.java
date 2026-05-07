package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(description = "Запрос для места")
@Data
public class SeatRequest {

    @Schema(description = "Id зала")
    @NotBlank(message = "Требуется id зала")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String hallId;

    @Schema(description = "Id типа места")
    @NotBlank(message = "Требуется id типа места")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String seatTypeId;

    @Schema(description = "Номер ряда")
    @NotNull(message = "Номер ряда должен быть больше 0")
    @Min(
            value = 1,
            message = "Номер ряда должен быть больше 0"
    )
    private int seatRow;

    @Schema(description = "Номер места в ряду")
    @NotNull(message = "Номер места в ряду должен быть больше 0")
    @Min(
            value = 1,
            message = "Номер места в ряду должен быть больше 0"
    )
    private int seatColumn;
}
