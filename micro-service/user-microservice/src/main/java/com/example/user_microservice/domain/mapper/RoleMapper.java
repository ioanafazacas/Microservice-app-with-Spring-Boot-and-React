package com.example.user_microservice.domain.mapper;

import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.User;
import com.example.user_microservice.domain.dto.RoleDTO;
import com.example.user_microservice.domain.dto.UserCreateDTO;
import com.example.user_microservice.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {

    public RoleDTO roleEntityToDto(Role role){
        return RoleDTO.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }
    public List<RoleDTO> roleListEntityToDto(List<Role> roles){
        return roles.stream()
                .map(role -> roleEntityToDto(role))
                .toList();
    }
    public Role roleDtoToEntity(RoleDTO role){
        return Role.builder().
                id(role.getId()).
                role(role.getRole())
                .build();
    }
}
