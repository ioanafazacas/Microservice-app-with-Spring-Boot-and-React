package com.example.flowerMicroservice.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {
    private int id;
    private String color;
    private int quantity ;
    private int flower_id;
    private int shop_id;
}
