package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Запрос для пользователя")
@Data
public class UserRequest {

    @Schema(description = "ФИО")
    @NotBlank(message = "Требуется ФИО")
    private String fullName;

    @Schema(description = "Электронная почта")
    @NotBlank(message = "Требуется электронная почта")
    @Email
    @Size(min = 5, max = 25, message = "Длина электронной почты должна быть от 5 до 25 символов")
    private String email;

    @Schema(description = "Номер телефона")
    @NotBlank(message = "Требуется номер телефона")
    @Pattern(
            regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Неверный формат номера телефона"
    )
    @Size(min = 11, max = 12, message = "Длина номера телефона должна быть от 11 до 12 символов")
    private String phoneNumber;

    @Schema(description = "Пароль")
    @NotBlank(message = "Требуется пароль")
    @Size(min = 6, max = 10, message = "Длина пароля должна быть от 6 до 10 символов")
    private String password;
}
