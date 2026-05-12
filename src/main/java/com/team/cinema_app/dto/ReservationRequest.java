package com.team.cinema_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Schema(description = "Запрос для брони")
@Data
public class ReservationRequest {

    @Schema(description = "id сеанса")
    @NotBlank(message = "Требуется id сеансов")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id Должен иметь формат UUID"
    )
    private String sessionId;

    @Schema(description = "id мест")
    @NotEmpty(message = "Список мест не должен быть пустым")
    private List<
            @Pattern(
                    regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                    message = "Id Должен иметь формат UUID"
            )
            String
            > seatsId;

}
