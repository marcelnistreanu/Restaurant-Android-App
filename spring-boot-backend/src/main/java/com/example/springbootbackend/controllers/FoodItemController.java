package com.example.springbootbackend.controllers;

import com.example.springbootbackend.entities.FoodItem;
import com.example.springbootbackend.repositories.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/food")
@RequiredArgsConstructor
@CrossOrigin
public class FoodItemController {

    private final FoodItemRepository foodRepository;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFoodItems(){
        return ResponseEntity.ok(foodRepository.findAll());
    }

}
