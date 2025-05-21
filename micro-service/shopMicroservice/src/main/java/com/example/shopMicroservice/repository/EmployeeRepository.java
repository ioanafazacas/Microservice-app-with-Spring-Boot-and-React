package com.example.shopMicroservice.repository;

import com.example.shopMicroservice.domain.entity.Employee;
import com.example.shopMicroservice.domain.entity.Shop;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.user_id = :userId")
    void deleteByUserId(int userId);

    @Query("SELECT e FROM Employee e WHERE e.user_id = :user_id")
    Optional<Employee> findByUserId(@Param("user_id") int user_id);

    @Query("SELECT e FROM Employee e WHERE e.shop = :shop")
    Employee findByShop(@Param("shop") Shop shop);
}
