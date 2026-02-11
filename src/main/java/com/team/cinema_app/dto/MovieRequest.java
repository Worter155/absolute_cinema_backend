package com.team.cinema_app.dto;

import lombok.Data;

@Data
public class MovieRequest {
    private String title;
    private Integer duration;
    private Integer ageLimit;
    private Long countryId;
    private Long genreId;
    private Long directorId;
    private Long filmCompanyId;
}