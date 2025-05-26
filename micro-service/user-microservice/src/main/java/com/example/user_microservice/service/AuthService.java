package com.example.user_microservice.service;

import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.User;
import com.example.user_microservice.domain.dto.LoginRequestDTO;
import com.example.user_microservice.domain.dto.RoleDTO;
import com.example.user_microservice.domain.dto.UserCreateDTO;
import com.example.user_microservice.domain.dto.UserDTO;
import com.example.user_microservice.infrastructure.config.JwtUtil;
import com.example.user_microservice.repository.RoleRepository;
import com.example.user_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(UserCreateDTO dto, Role role) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(role)
                .build();
        userRepo.save(user);
        return "User registered!";
    }

    public String login(LoginRequestDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleDTO roleDTO= RoleDTO.builder()
                .id(user.getRole().getId())
                .role(user.getRole().getRole())
                .build();
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(roleDTO).build();
    }

    public String deleteById(int id) {
        userRepo.deleteById(id);
        return "User deleted";
    }

    public String updateUser(int id, UserDTO userDTO) {
        Optional<User> existing = userRepo.findById(id);
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not update. User not found with id: " + id);
        }

        userRepo.updateUser(id, userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        return "User Actualizat cu succes.";
    }
}
