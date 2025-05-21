package com.example.shopMicroservice.domain.mapper;

import com.example.shopMicroservice.domain.entity.Shop;
import com.example.shopMicroservice.domain.dto.ShopDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopMapper {
    public ShopDTO shopEntityToDto(Shop shop){
        return ShopDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .build();
    }
    public List<ShopDTO> shopListEntityToDto(List<Shop> shops){
        return shops.stream()
                .map(shop -> shopEntityToDto(shop))
                .toList();
    }
    public Shop shopDtoToEntity(ShopDTO shopDTO){
        return Shop.builder()
                .id(shopDTO.getId())
                .name(shopDTO.getName())
                .address(shopDTO.getAddress())
                .build();
    }
}
