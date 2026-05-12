package com.team.cinema_app.service;

import com.team.cinema_app.dto.UpdateUserRequest;
import com.team.cinema_app.dto.UserResponse;
import com.team.cinema_app.exception.UserNotFoundException;
import com.team.cinema_app.mapper.UserMapper;
import com.team.cinema_app.model.User;
import com.team.cinema_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id " + id));

        return userMapper.toResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с электронной почтой  " + email));

        return userMapper.toResponse(user);
    }

    public UserResponse getCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с почтой " + email));

        return userMapper.toResponse(user);
    }

    public UserResponse updateCurrentUser(UpdateUserRequest request, Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с почтой " + email));

        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User updated = userRepository.save(user);

        return userMapper.toResponse(updated);
    }

    public UserResponse updateUserById(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id " + id));

        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User updated = userRepository.save(user);

        return userMapper.toResponse(updated);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
