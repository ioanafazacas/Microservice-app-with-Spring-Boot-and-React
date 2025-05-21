package com.example.shopMicroservice.repository;

import com.example.shopMicroservice.domain.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
