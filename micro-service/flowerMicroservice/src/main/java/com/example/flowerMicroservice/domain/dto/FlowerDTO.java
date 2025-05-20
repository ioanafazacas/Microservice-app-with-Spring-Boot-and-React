package com.example.flowerMicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {
    private int id;
    private String name;
    private float purchase_price;
    private float sale_price;
    private String image;
}
