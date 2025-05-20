package com.example.flowerMicroservice.infrastructure.repository;

import com.example.flowerMicroservice.domain.Flower;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Flower f SET f.name = :name, f.purchase_price = :purchasePrice, f.sale_price = :salePrice, f.image = :image WHERE f.id = :id")
    void updateFlower(@Param("id") int id,
                      @Param("name") String name,
                      @Param("purchasePrice") float purchasePrice,
                      @Param("salePrice") float salePrice,
                      @Param("image") String image);

    @Query("SELECT f FROM Flower f WHERE f.name = :name")
    Flower findByName(String name);
}
