package com.example.user_microservice.controller;


import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.dto.LoginRequestDTO;
import com.example.user_microservice.domain.dto.UserCreateDTO;
import com.example.user_microservice.domain.dto.UserDTO;
import com.example.user_microservice.repository.RoleRepository;
import com.example.user_microservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;
    private final RoleRepository roleRepo;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateDTO dto) {
        if(dto.getRoleId()==0)dto.setRoleId(1);
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return ResponseEntity.ok(authService.register(dto, role));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        System.out.println("login???");
        String jwt = authService.login(dto);
        return ResponseEntity.ok(jwt);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','EMPLOYEE')")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        System.out.println(email);
        UserDTO userDto = authService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        return ResponseEntity.ok(authService.deleteById(id));
    }

    @PreAuthorize("hasAnyRole()")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateFlower(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.updateUser(id,userDTO));
    }


}
