package com.team.cinema_app.controller;

import com.team.cinema_app.dto.UpdateUserRequest;
import com.team.cinema_app.dto.UserRequest;
import com.team.cinema_app.dto.UserResponse;
import com.team.cinema_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Пользователи",
        description = "Все методы для работы с пользователями"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получить всех пользователей")
    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Operation(summary = "Получить пользователя по id")
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @Operation(summary = "Получить пользователя по email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable @Email String email){
        return ResponseEntity.ok().body(userService.getUserByEmail(email));
    }

    @Operation(summary = "Получить залогиненого пользователя(себя(для лк))")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok().body(userService.getCurrentUser(authentication));
    }

    @Operation(summary = "Изменить залогиненого пользователя(себя(для лк))")
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateCurrentUser(@Valid @RequestBody UpdateUserRequest request, Authentication authentication){
        return ResponseEntity.ok().body(userService.updateCurrentUser(request, authentication));
    }

    @Operation(summary = "Изменить пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok().body(userService.updateUserById(id,request));
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
