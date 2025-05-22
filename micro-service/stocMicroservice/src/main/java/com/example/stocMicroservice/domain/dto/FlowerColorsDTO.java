package com.example.stocMicroservice.domain.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerColorsDTO {
    private int id;
    private String name;
    private float purchase_price;
    private float sale_price;
    private String image;
    private List<String> colors;
}
