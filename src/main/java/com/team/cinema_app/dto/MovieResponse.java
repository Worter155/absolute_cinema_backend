package com.team.cinema_app.dto;

import lombok.Data;

@Data
public class MovieResponse {
    private String id;
    private String title;
    private Integer duration;
    private Integer ageLimit;
    private String countryTitle;
    private String genreTitle;
    private String directorName;
    private String filmCompanyTitle;
    private String posterUrl;
}
