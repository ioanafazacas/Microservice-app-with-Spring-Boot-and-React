package com.example.flowerMicroservice.domain.mapper;

import com.example.flowerMicroservice.domain.Flower;
import com.example.flowerMicroservice.domain.dto.FlowerDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlowerMapper {
    public FlowerDTO flowerEntityToDto(Flower flower){
        return FlowerDTO.builder()
                .id(flower.getId())
                .name(flower.getName())
                .sale_price(flower.getSale_price())
                .purchase_price(flower.getPurchase_price())
                .image(flower.getImage())
                .build();
    }
    public List<FlowerDTO> flowerListEntityToDto(List<Flower> flowers){
        return flowers.stream()
                .map(flower -> flowerEntityToDto(flower))
                .toList();
    }
    public Flower flowerDtoToEntity(FlowerDTO flowerDTO){
        return Flower.builder()
                .id(flowerDTO.getId())
                .name(flowerDTO.getName())
                .sale_price(flowerDTO.getSale_price())
                .purchase_price(flowerDTO.getPurchase_price())
                .image(flowerDTO.getImage())
                .build();
    }
}
