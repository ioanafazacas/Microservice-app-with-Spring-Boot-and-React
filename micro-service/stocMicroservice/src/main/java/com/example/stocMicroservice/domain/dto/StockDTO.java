package com.example.stocMicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private int id;
    private String color;
    private int quantity ;
    private int flower_id;
    private int shop_id;
}
