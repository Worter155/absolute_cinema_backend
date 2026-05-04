package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Запрос для фильма")
@Data
public class MovieRequest {

    @Schema(description = "Название")
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Schema(description = "Длительность в минутах")
    @NotNull(message = "Duration is required")
    private Integer duration;

    @Schema(description = "Возрастное ограничение")
    @NotNull(message = "Age limit is required")
    private Integer ageLimit;

    @Schema(description = "id страны")
    @NotBlank(message = "Country id is required")
    private String countryId;

    @Schema(description = "id жанра")
    @NotBlank(message = "Genre id is required")
    private String genreId;

    @Schema(description = "id режиссера")
    @NotBlank(message = "Director id is required")
    private String directorId;

    @Schema(description = "id кинокомпании")
    @NotBlank(message = "Film company id is required")
    private String filmCompanyId;
}