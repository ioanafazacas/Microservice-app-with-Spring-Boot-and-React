package com.example.stocMicroservice.client;

import com.example.stocMicroservice.domain.dto.FlowerDTO;
import com.example.stocMicroservice.domain.dto.ShopDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "shop-service", url ="http://localhost:8083/api/shops")
public interface ShopClient {
    @GetMapping("/all")
    List<ShopDTO> getAllShops();

    @GetMapping("/{id}")
    ShopDTO getShopById(@PathVariable int id);
}