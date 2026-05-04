package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Запрос для режиссера")
@Data
public class DirectorRequest {
    @Schema(description = "Название")
    @NotBlank(message = "Требуется имя")
    @Size(max = 100, message = "Имя должно быть короче 100 символов")
    private String name;
}
