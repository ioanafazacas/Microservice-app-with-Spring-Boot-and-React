package com.example.stocMicroservice.domain.mapper;

import com.example.stocMicroservice.domain.Stock;
import com.example.stocMicroservice.domain.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockMapper {
    public StockDTO stockEntityToDto(Stock stock){
        return StockDTO.builder()
                .id(stock.getId())
                .color(stock.getColor())
                .quantity(stock.getQuantity())
                .flower_id(stock.getFlower_id())
                .shop_id(stock.getShop_id())
                .build();
    }
    public List<StockDTO> stockListEntityToDto(List<Stock> stocks){
        return stocks.stream()
                .map(stock -> stockEntityToDto(stock))
                .toList();
    }
    public Stock stockDtoToEntity(StockDTO stockDTO){
        return Stock.builder()
                .id(stockDTO.getId())
                .color(stockDTO.getColor())
                .quantity(stockDTO.getQuantity())
                .flower_id(stockDTO.getFlower_id())
                .shop_id(stockDTO.getShop_id())
                .build();
    }
}
