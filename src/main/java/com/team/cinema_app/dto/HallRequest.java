package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(description = "Зарос для зала")
@Data
public class HallRequest {

    @Schema(description = "Название")
    @NotBlank(message = "Требуется название")
    @Size(max = 100, message = "TНазвание должно быть короче 100 символов")
    private String title;

    @Schema(description = "Id типа зала")
    @NotBlank(message = "Требуется id типа зала")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String hallTypeId;

    @Schema(description = "Количество рядов")
    @NotNull(message = "Количество рядов должно быть больше 0")
    @Min(
            value = 1,
            message = "Количество рядов должно быть больше 0"
    )
    private int rows;

    @Schema(description = "Количество мест в ряду")
    @NotNull(message = "Количество мест в ряду должно быть больше 0")
    @Min(
            value = 1,
            message = "Количество мест в ряду должно быть больше 0"
    )
    private int columns;
}
