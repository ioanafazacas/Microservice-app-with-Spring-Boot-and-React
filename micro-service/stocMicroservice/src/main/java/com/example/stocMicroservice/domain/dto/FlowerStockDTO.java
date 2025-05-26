package com.example.stocMicroservice.domain.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerStockDTO {
    private int stock_id;
    private int flower_id;
    private int shop_id;
    private String name;
    private float purchase_price;
    private float sale_price;
    private String image;
    private String color;
    private int quantity;
}
