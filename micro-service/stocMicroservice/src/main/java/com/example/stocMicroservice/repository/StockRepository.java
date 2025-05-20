package com.example.stocMicroservice.repository;

import com.example.stocMicroservice.domain.Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Stock s WHERE s.flower_id = :flowerId")
    void deleteByFlowerId(int flowerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Stock s WHERE s.shop_id = :shopId")
    void deleteByShopId(int shopId);

    //Returnează toate florile cu o anumită culoare
    @Query("SELECT s FROM Stock s WHERE s.color = :color")
    List<Stock> findByColor(@Param("color") String color);

    @Query("SELECT s FROM Stock s WHERE s.quantity = :quantity")
    List<Stock> findByQuantity(@Param("quantity") int quantity);

    @Query("SELECT s FROM Stock s WHERE s.shop_id = :shop_id")
    List<Stock> findByShopId(@Param("shop_id") int shop_id);

    @Query("SELECT s FROM Stock s WHERE s.flower_id = :flower_id")
    List<Stock> findByFlowerId(@Param("flower_id") int flower_id);

    // Returnează toate florile care au quantity = 0 (epuizate)
    @Query("SELECT s FROM Stock s WHERE s.quantity = 0")
    List<Stock> findOutOfStockFlowers();

    // Returnează toate florile care au quantity > 0 (în stoc)
    @Query("SELECT s FROM Stock s WHERE s.quantity > 0")
    List<Stock> findAvailableFlowers();

    @Query("SELECT s FROM Stock s WHERE s.color = :color AND s.shop_id = :shop_id")
    List<Stock> findByColorAndShop(@Param("color") String color, @Param("shop_id") int shop_id);

    // Returnează toate florile care au quantity = 0 dintr-o anumită florărie
    @Query("SELECT s FROM Stock s WHERE s.quantity = 0 AND s.shop_id = :shop_id")
    List<Stock> findOutOfStockFlowersByShop(@Param("shop_id") int shop_id);

    // Returnează toate florile care au quantity > 0 dintr-o anumită florărie
    @Query("SELECT s FROM Stock s WHERE s.quantity > 0 AND s.shop_id = :shop_id")
    List<Stock> findAvailableFlowersByShop(@Param("shop_id") int shop_id);

    // Returnează toate variantele disponibile de culori ale unei flori cautata intr-o anumit florarie
    @Query("SELECT s FROM Stock s WHERE s.quantity > 0 AND s.shop_id = :shop_id AND s.flower_id = :flower_id")
    List<Stock> findAvailableFlowersByShopAndFlowerId(@Param("shop_id") int shop_id,
                                                      @Param("flower_id") int flower_id);

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.quantity = :quantity WHERE s.id = :id")
    void updateQuantity(@Param("id") int id,
                      @Param("quantity") int quantity
                      );
}
