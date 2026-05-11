package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Запрос для входа")
@Data
public class LoginRequest {

    @Schema(description = "Электронная почта")
    @NotBlank(message = "Требуется электронная почта")
    @Email
    private String email;

    @Schema(description = "Пароль")
    @NotBlank(message = "Требуется пароль")
    private String password;
}