package com.example.flowerMicroservice.service;

import com.example.flowerMicroservice.domain.Flower;

import com.example.flowerMicroservice.domain.dto.FlowerDTO;
import com.example.flowerMicroservice.domain.mapper.FlowerMapper;
import com.example.flowerMicroservice.infrastructure.repository.RepoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlowerService {
    //private final FlowerRepository flowerRepository;
    private final RepoAdapter flowerRepository;
    private final FlowerMapper mapper;
    private final RestTemplate restTemplate;
    public void save(FlowerDTO flower){
        flowerRepository.save(mapper.flowerDtoToEntity(flower));
    }
    public void delete(FlowerDTO flower){
        flowerRepository.delete(mapper.flowerDtoToEntity(flower));
    }
    public void deleteById(int id){
        flowerRepository.deleteById(id);
    }
    public FlowerDTO findById(int id){
        Optional<Flower> existing = flowerRepository.findById(id);
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flower not found with id: " + id);
        }
        return mapper.flowerEntityToDto(existing.get());
    }

    public FlowerDTO findByName(String name){
        Flower flower = flowerRepository.findByName(name);
        if(flower==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flower not found with name: " + name);
        }
        return mapper.flowerEntityToDto(flower);
    }
    public List<FlowerDTO> findAll(){
        return mapper.flowerListEntityToDto(flowerRepository.findAll());
    }

    public void updateFlower(int id, FlowerDTO updatedFlower) {
        Optional<Flower> existing = flowerRepository.findById(id);
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not update. Flower not found with id: " + id);
        }

        /*flowerRepository.updateFlower(id, updatedFlower.getName(), updatedFlower.getPurchase_price(),
                updatedFlower.getSale_price(), updatedFlower.getImage());*/
        flowerRepository.updateFlower(id, mapper.flowerDtoToEntity(updatedFlower));
    }
/*
    public FlowerResponeDTO getFlowerWithStock(int flowerId){
        FlowerResponeDTO responeDTO = new FlowerResponeDTO();
        Optional<Flower> flower = flowerRepository.findById(flowerId);
        if (flower.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flower not found with id: " + flowerId);
        }
        FlowerDTO flowerDTO = mapper.flowerEntityToDto(flower.get());

        ResponseEntity<List<StockDTO>> responseEntity = restTemplate.
                getForEntity("http://localhost8081/stock/flower-colors/"+ flowerId, StockDTO.class);

        StockDTO stockDTO = responseEntity.getBody();
        System.out.println(responseEntity.getStatusCode());

        responeDTO.setFlower(flowerDTO);
        responeDTO.setStock(stockDTO);

        return responeDTO;
    }
*/
}
