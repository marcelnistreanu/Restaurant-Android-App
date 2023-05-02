package com.example.springbootbackend.repositories;

import com.example.springbootbackend.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    Optional<FoodItem> findByFoodName(String foodName);
}
