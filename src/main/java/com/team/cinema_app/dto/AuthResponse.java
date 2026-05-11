package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Ответ с токеном")
@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    @Schema(description = "Токен")
    private String token;
}
