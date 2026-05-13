package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(description = "Запрос для фильма")
@Data
public class MovieRequest {

    @Schema(description = "Название")
    @NotBlank(message = "Требуется название")
    @Size(max = 100, message = "Название должно быть короче 100 символов")
    private String title;

    @Schema(description = "Длительность в минутах")
    @NotNull(message = "Требуется длительность")
    @Min(
            value = 250,
            message = "Минимальная длительность фильма 50 мин"
    )
    @Max(
            value = 600,
            message = "Максимальная длительность фильма 270 мин"
    )
    private Integer duration;

    @Schema(description = "Возрастное ограничение")
    @NotNull(message = "Требуется возрастное ограничение")
    @Min(0)
    private Integer ageLimit;

    @Schema(description = "id страны")
    @NotBlank(message = "Требуется id страны")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String countryId;

    @Schema(description = "id жанра")
    @NotBlank(message = "Требуется id жанра")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String genreId;

    @Schema(description = "id режиссера")
    @NotBlank(message = "Требуется id режиссера")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String directorId;

    @Schema(description = "id кинокомпании")
    @NotBlank(message = "Требуется id кинокомпании")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String filmCompanyId;
}