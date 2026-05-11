package com.team.cinema_app.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Запрос для смены роли")
@Data
public class ChangeRoleRequest {

    @Schema(description = "Электронная почта")
    @Email
    @NotBlank(message = "Требуется электронная почта")
    private String email;
}
