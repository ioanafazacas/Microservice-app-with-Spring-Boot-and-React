package com.example.user_microservice.repository;

import com.example.user_microservice.domain.Role;
import com.example.user_microservice.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.role= :role")
    List<User> findByRole(Role role);

    @Query("SELECT u FROM User u WHERE u.email = :email and u.password = :password")
    Optional<User> findByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password= :password, u.role= :role WHERE u.id = :id")
    void updateUser(@Param("id") int id,
                    @Param("name") String name,
                    @Param("email")String email,
                    @Param("password")String password,
                    @Param("role")Role role);
}



