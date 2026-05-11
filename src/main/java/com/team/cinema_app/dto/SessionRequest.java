package com.team.cinema_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "Запрос для сеанса")
@Data
public class SessionRequest {

    @Schema(description = "id фильма")
    @NotBlank(message = "Требуется id фильма")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String movieId;

    @Schema(description = "id зала")
    @NotBlank(message = "Требуется id зала")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String hallId;

    @Schema(description = "Дата и время сеанса")
    @NotBlank(message = "Требуется дата и время сеанса")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String dateTime;

    @Schema(description = "Базовая цена")
    @NotNull(message = "Требуется базовая цена")
    private double basePrice;
}
