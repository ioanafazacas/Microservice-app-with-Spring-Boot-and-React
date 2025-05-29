package com.example.flowerMicroservice.controller;

import com.example.flowerMicroservice.domain.dto.FlowerDTO;

import com.example.flowerMicroservice.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flowers")
public class FlowerController {
    @Autowired
    private FlowerService flowerService;


    @PostMapping
    public void save(@RequestBody FlowerDTO flowerDTO){
        flowerService.save(flowerDTO);
    }

    @DeleteMapping
    public void delete(@RequestBody FlowerDTO flowerDTO){
        flowerService.delete(flowerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        flowerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateFlower(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) {
        flowerService.updateFlower(id, flowerDTO);
    }

    @GetMapping("/all")
    public List<FlowerDTO> getAllFlowers() {
        return flowerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowerDTO> getFlowerById(@PathVariable int id) {
        return ResponseEntity.ok(flowerService.findById(id));
    }
/*
    @GetMapping("/full-info/{id}")
    public ResponseEntity<FlowerResponeDTO> getFullFlowerById(@PathVariable int id) {
        return ResponseEntity.ok(flowerService.getFlowerWithStock(id));
    }
*/
    @GetMapping("/name/{name}")
    public ResponseEntity<FlowerDTO> getFlowerByName(@PathVariable String name) {
        return ResponseEntity.ok(flowerService.findByName(name));
    }


}
