package com.example.shopMicroservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private int user_id;
    @ManyToOne
    @JoinColumn(name="shop_id", nullable=false)
    private Shop shop;
}
