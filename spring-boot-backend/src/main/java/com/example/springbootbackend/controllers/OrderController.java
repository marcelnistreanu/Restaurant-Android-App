package com.example.springbootbackend.controllers;

import com.example.springbootbackend.entities.Order;
import com.example.springbootbackend.entities.OrderItem;
import com.example.springbootbackend.entities.TableEntity;
import com.example.springbootbackend.repositories.OrderItemRepository;
import com.example.springbootbackend.repositories.OrderRepository;
import com.example.springbootbackend.repositories.TableRepository;
import com.example.springbootbackend.services.FirebaseMessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/food")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final TableRepository tableRepository;

    private final FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/sendOrder")
    public ResponseEntity<?> sendOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        double totalAmount = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
            totalAmount += orderItem.getFoodItem().getPrice();
        }
        savedOrder.setTotalAmount(totalAmount);
        orderRepository.save(savedOrder);
        TableEntity table = order.getTable();
        table.setStatus("OCCUPIED");
        tableRepository.save(table);

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

    @PostMapping("/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        ArrayList<OrderItem> orderItems = orderItemRepository.findByOrder(order);
        orderItemRepository.deleteAll(orderItems);
        orderRepository.delete(order);
        TableEntity table = order.getTable();
        table.setStatus("AVAILABLE");
        tableRepository.save(table);


        return ResponseEntity.ok("Order deleted successfully");
    }

    @PostMapping("/sendReadyOrder/{orderId}")
    public ResponseEntity<?> readySendOrder(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setStatus("READY");
        orderRepository.save(order);
        firebaseMessagingService.sendNotificationByToken(orderId);
        return ResponseEntity.ok("Order set to ready");

    }

}
