package com.example.user_microservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id") // cheia străină în tabelul user
    private Role role;
}
