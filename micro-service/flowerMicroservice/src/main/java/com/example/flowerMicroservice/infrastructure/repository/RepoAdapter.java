package com.example.flowerMicroservice.infrastructure.repository;

import com.example.flowerMicroservice.domain.Flower;
import com.example.flowerMicroservice.domain.contracts.GenericRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepoAdapter implements GenericRepo {
    @Autowired
    private FlowerRepository flowerRepository;
    @Override
    public void save(Flower flower) {
        flowerRepository.save(flower);
    }

    @Override
    public void delete(Flower flower) {
        flowerRepository.delete(flower);
    }

    @Override
    public void deleteById(int id) {
        flowerRepository.deleteById(id);
    }

    @Override
    public Optional<Flower> findById(int id) {
        return flowerRepository.findById(id);
    }

    @Override
    public Flower findByName(String name) {
        return flowerRepository.findByName(name);
    }

    @Override
    public List<Flower> findAll() {
        return flowerRepository.findAll();
    }

    @Override
    public void updateFlower(int id, Flower updatedFlower) {
        flowerRepository.updateFlower(id, updatedFlower.getName(), updatedFlower.getPurchase_price(),
                updatedFlower.getSale_price(), updatedFlower.getImage());
    }
}
