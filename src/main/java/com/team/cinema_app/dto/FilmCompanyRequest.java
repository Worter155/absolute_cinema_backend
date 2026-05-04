package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Запрос для кинокомпании")
@Data
public class FilmCompanyRequest {
    @Schema(description = "Название")
    @NotBlank(message = "Требуется название")
    @Size(max = 100, message = "Название должно быть короче 100 символов")
    private String title;
}
