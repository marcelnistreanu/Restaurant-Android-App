package com.example.springbootbackend.controllers;

import com.example.springbootbackend.entities.Order;
import com.example.springbootbackend.repositories.OrderItemRepository;
import com.example.springbootbackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/food")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @PostMapping("/sendOrder")
    public ResponseEntity<?> sendOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return ResponseEntity.ok("Order sent successfully");
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/getAllOrderItems")
    public ResponseEntity<?> getAllOrderItems() {
        return ResponseEntity.ok(orderItemRepository.findAll());
    }
}
