package com.example.shopMicroservice.domain.dto;

import lombok.*;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private int id;
    private int user_id;
    private ShopDTO shop;
}
