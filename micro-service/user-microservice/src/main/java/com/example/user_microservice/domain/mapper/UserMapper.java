package com.example.user_microservice.domain.mapper;

import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.User;
import com.example.user_microservice.domain.dto.RoleDTO;
import com.example.user_microservice.domain.dto.UserCreateDTO;
import com.example.user_microservice.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDTO userEntityToDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(RoleDTO.builder()
                        .id(user.getRole().getId())
                        .role(user.getRole().getRole())
                        .build())
                .build();
    }
    public List<UserDTO> userListEntityToDto(List<User> users){
        return users.stream()
                .map(user -> userEntityToDto(user))
                .toList();
    }
    public User userDtoToEntity(UserCreateDTO dto, Role role){
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(role)
                .build();
    }
}
