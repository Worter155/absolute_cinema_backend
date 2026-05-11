package com.team.cinema_app.controller;

import com.team.cinema_app.dto.ChangeRoleRequest;
import com.team.cinema_app.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Выдача прав админа",
        description = "Метод для выдачи прав админа"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthService authService;

    @Operation(summary = "Выдача прав админа")
    @PostMapping("/give-admin")
    public ResponseEntity<String> giveAdminRole(@Valid @RequestBody ChangeRoleRequest request) {

        authService.giveAdminRole(request);

        return ResponseEntity.ok("Роль админа выдана пользователю " + request.getEmail());
    }
}