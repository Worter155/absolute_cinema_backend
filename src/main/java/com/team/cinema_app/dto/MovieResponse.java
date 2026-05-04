package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Ответ с фильмом")
@Data
public class MovieResponse {
    @Schema(description = "id")
    private String id;

    @Schema(description = "Название")
    private String title;

    @Schema(description = "Длительность")
    private Integer duration;

    @Schema(description = "Возрастное ограничение")
    private Integer ageLimit;

    @Schema(description = "Страна")
    private String countryTitle;

    @Schema(description = "Жанр")
    private String genreTitle;

    @Schema(description = "Режиссер")
    private String directorName;

    @Schema(description = "Кинокомпания")
    private String filmCompanyTitle;

    @Schema(description = "Ссылка на постер")
    private String posterUrl;
}
