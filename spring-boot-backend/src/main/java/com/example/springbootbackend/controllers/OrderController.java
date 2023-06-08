package com.example.springbootbackend.controllers;

import com.example.springbootbackend.MessageResponse;
import com.example.springbootbackend.entities.Order;
import com.example.springbootbackend.repositories.OrderItemRepository;
import com.example.springbootbackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/food")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;


    @PostMapping("/sendOrder")
    public ResponseEntity<?> sendOrder(@RequestBody Order order) {
        orderService.sendOrder(order);
        return ResponseEntity.ok(new MessageResponse("Order sent successfully"));
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getAllOrderItems")
    public ResponseEntity<?> getAllOrderItems() {
        return ResponseEntity.ok(orderItemRepository.findAll());
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(new MessageResponse("Order deleted successfully."));
    }

    @PutMapping("/sendReadyOrder/{orderId}")
    public ResponseEntity<?> updateOrderToReady(@PathVariable Long orderId) {
        orderService.updateOrderToReady(orderId);
        return ResponseEntity.ok(new MessageResponse("Order's status set to ready"));
    }

    @PutMapping("/sendServedOrder/{orderId}")
    public ResponseEntity<?> servedSendOrder(@PathVariable Long orderId) {
        orderService.updateOrderToServed(orderId);
        return ResponseEntity.ok(new MessageResponse("Order's status set to served"));
    }

    @PutMapping("/sendPaidOrder/{orderId}")
    public ResponseEntity<?> paidSendOrder(@PathVariable Long orderId) {
        orderService.updateOrderToPaid(orderId);
        return ResponseEntity.ok(new MessageResponse("Order's status set to paid"));
    }

}
