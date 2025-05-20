package com.example.flowerMicroservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flower")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @Builder.Default
    private float purchase_price = 0.0f;
    @Builder.Default
    private float sale_price =0.0f;
    private String image;
}
