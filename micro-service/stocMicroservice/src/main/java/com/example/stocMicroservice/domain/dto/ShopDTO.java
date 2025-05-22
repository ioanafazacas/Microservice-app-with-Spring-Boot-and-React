package com.example.stocMicroservice.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    private int id;
    private String name;
    private String address;
}
