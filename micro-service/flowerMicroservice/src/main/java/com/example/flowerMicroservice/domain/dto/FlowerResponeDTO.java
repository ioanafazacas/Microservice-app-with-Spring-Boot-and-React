package com.example.flowerMicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerResponeDTO {
    private FlowerDTO flower;
    private  StockDTO stock;
}
