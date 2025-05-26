package com.example.user_microservice.service;

import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.User;
import com.example.user_microservice.domain.dto.LoginRequestDTO;
import com.example.user_microservice.domain.dto.RoleDTO;
import com.example.user_microservice.domain.dto.UserCreateDTO;
import com.example.user_microservice.domain.dto.UserDTO;
import com.example.user_microservice.domain.mapper.UserMapper;
import com.example.user_microservice.infrastructure.config.JwtUtil;
import com.example.user_microservice.repository.RoleRepository;
import com.example.user_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

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
        String newPassword = userDTO.getPassword();
        String finalPassword;

        if (!passwordEncoder.matches(newPassword, existing.get().getPassword())) {
            finalPassword = passwordEncoder.encode(newPassword); // s-a schimbat
        } else {
            finalPassword = existing.get().getPassword(); // e deja criptată și la fel
        }

        Role role = Role.builder().
                id(userDTO.getRole().getId()).
                role(userDTO.getRole().getRole())
                .build();
        userRepo.updateUser(id, userDTO.getName(), userDTO.getEmail(), finalPassword, role);
        return "User Actualizat cu succes.";
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.userListEntityToDto(users);
    }

    public List<UserDTO> getAllByRole(int roleId) {
        Optional<Role> role = roleRepo.findById(roleId);
        if (role.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not find role with id: " + roleId);
        }
        List<User> users = userRepo.findByRole(role.get());
        return userMapper.userListEntityToDto(users);
    }
}
