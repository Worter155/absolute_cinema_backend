package com.team.cinema_app.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DirectorResponse {
    private UUID id;
    private String name;
}
