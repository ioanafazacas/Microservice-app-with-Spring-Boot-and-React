package com.example.stocMicroservice.client;

import com.example.stocMicroservice.domain.dto.FlowerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "flower-service", url ="http://localhost:8081/api/flowers")
public interface FlowerClient {
    @GetMapping("/all")
    List<FlowerDTO> getAllFlowers();

    @GetMapping("/name/{name}")
    FlowerDTO getFlowerByName(@PathVariable String name);
}
