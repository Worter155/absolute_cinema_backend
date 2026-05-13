package com.team.cinema_app.service;

import com.team.cinema_app.dto.AuthResponse;
import com.team.cinema_app.dto.ChangeRoleRequest;
import com.team.cinema_app.dto.LoginRequest;
import com.team.cinema_app.dto.UserRequest;
import com.team.cinema_app.exception.EmailAlreadyTakenException;
import com.team.cinema_app.exception.MaxHallsCountException;
import com.team.cinema_app.exception.MaxUsersCountException;
import com.team.cinema_app.exception.UserNotFoundException;
import com.team.cinema_app.mapper.UserMapper;
import com.team.cinema_app.model.User;
import com.team.cinema_app.model.enums.Role;
import com.team.cinema_app.repository.UserRepository;
import com.team.cinema_app.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final long MAX_USERS = 1000;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthResponse register(UserRequest request) {

        if (userRepository.count() >= MAX_USERS) {
            throw new MaxUsersCountException("Превышено максимальное количество пользователей (%s)".formatted(MAX_USERS));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyTakenException("Электронная почта уже занята " + request.getEmail());
        }

        User user = userMapper.toEntity(request);

        userRepository.save(user);

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.List.of()
                )
        );

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails user =
                userDetailsService.loadUserByUsername(
                        request.getEmail()
                );

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public void giveAdminRole(ChangeRoleRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с почтой " + request.getEmail()));

        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }
}