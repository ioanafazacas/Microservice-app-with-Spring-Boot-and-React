package com.example.shopMicroservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private String address;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Employee> employees;
}
