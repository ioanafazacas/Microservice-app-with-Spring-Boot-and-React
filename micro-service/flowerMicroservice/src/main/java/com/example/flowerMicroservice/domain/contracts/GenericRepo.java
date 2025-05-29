package com.example.flowerMicroservice.domain.contracts;

import com.example.flowerMicroservice.domain.Flower;
import com.example.flowerMicroservice.domain.dto.FlowerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface GenericRepo {
    void save(Flower flower);
    void delete(Flower flower);
    void deleteById(int id);
    public Optional<Flower> findById(int id);

    public Flower findByName(String name);
    public List<Flower> findAll();

    public void updateFlower(int id, Flower updatedFlower);
}
