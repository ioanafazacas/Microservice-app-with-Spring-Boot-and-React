package com.example.shopMicroservice.service;


import com.example.shopMicroservice.domain.entity.Shop;
import com.example.shopMicroservice.domain.dto.ShopDTO;
import com.example.shopMicroservice.domain.mapper.ShopMapper;
import com.example.shopMicroservice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopMapper mapper;

    public List<ShopDTO> findAll(){
        return mapper.shopListEntityToDto(shopRepository.findAll());
    }

    public ShopDTO findById(int id){
        Optional<Shop> shopOptional = shopRepository.findById(id);
        if(shopOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Shop not found with id: " + id);
        }
        return mapper.shopEntityToDto(shopOptional.get());
    }

}
