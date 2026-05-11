package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Schema(description = "Ответ с пользователем")
@Data
public class UserResponse {

    @Schema(description = "id")
    private UUID id;

    @Schema(description = "ФИО")
    private String fullName;

    @Schema(description = "Электронная почта")
    private String email;

    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Schema(description = "Роль")
    private String role;
}
