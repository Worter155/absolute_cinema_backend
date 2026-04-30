package com.team.cinema_app.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FilmCompanyResponse {
    private UUID id;
    private String title;
}
