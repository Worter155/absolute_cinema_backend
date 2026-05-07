package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Запрос для типа места")
@Data
public class SeatTypeRequest {
    @Schema(description = "Название")
    @NotBlank(message = "Требуется название")
    @Size(max = 100, message = "TНазвание должно быть короче 100 символов")
    private String title;

    @Schema(description = "Множитель цены")
    @NotNull(message = "Требуется множитель цены")
    @Min(
            value = 1,
            message = "Множитель должен быть больше 1"
    )
    private double priceMultiplier;
}
