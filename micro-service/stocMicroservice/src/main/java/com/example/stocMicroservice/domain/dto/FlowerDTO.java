package com.example.stocMicroservice.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {
    private int id;
    private String name;
    private float purchase_price;
    private float sale_price;
    private String image;
}
