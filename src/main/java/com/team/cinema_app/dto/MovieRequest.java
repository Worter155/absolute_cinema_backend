package com.team.cinema_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotNull(message = "Duration is required")
    private Integer duration;

    @NotNull(message = "Age limit is required")
    private Integer ageLimit;

    @NotBlank(message = "Country id is required")
    private String countryId;

    @NotBlank(message = "Genre id is required")
    private String genreId;

    @NotBlank(message = "Director id is required")
    private String directorId;

    @NotBlank(message = "Film company id is required")
    private String filmCompanyId;
}