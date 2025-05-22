package com.example.stocMicroservice.domain.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopColorsDTO {
    private String name;
    private String address;
    private List<String> colors;
}
