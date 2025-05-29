package com.example.user_microservice.controller;


import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.dto.LoginRequestDTO;
import com.example.user_microservice.domain.dto.RoleDTO;
import com.example.user_microservice.domain.dto.UserCreateDTO;
import com.example.user_microservice.domain.dto.UserDTO;
import com.example.user_microservice.repository.RoleRepository;
import com.example.user_microservice.service.AuthService;
import lombok.RequiredArgsConstructor;
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
        System.out.println(dto);
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        System.out.println(email);
        UserDTO userDto = authService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','EMPLOYEE')")
    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = authService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> userDtos = authService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all/byRole/{roleId}")
    public ResponseEntity<List<UserDTO>> getAllByRole(@PathVariable int roleId) {
        List<UserDTO> userDtos = authService.getAllByRole(roleId);
        return ResponseEntity.ok(userDtos);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        return ResponseEntity.ok(authService.deleteById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateFlower(@PathVariable int id, @RequestBody UserCreateDTO userDTO) {
        System.out.println("userDTO");
        System.out.println(userDTO);
        System.out.println(userDTO.getRoleId());
        return ResponseEntity.ok(authService.updateUser(id,userDTO));
    }


}
