package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(description = "Запрос для изменения пользователя")
@Data
public class UpdateUserRequest {

    @Schema(description = "ФИО")
    @NotBlank(message = "Требуется ФИО")
    private String fullName;

    @Schema(description = "Номер телефона")
    @NotBlank(message = "Требуется номер телефона")
    @Pattern(
            regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Неверный формат номера телефона"
    )
    private String phoneNumber;

    @Schema(description = "Пароль")
    @NotBlank(message = "Требуется пароль")
    private String password;
}
