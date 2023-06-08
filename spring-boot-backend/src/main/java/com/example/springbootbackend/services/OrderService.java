package com.example.springbootbackend.services;

import com.example.springbootbackend.entities.Order;
import com.example.springbootbackend.entities.OrderItem;
import com.example.springbootbackend.entities.OrderStatus;
import com.example.springbootbackend.entities.TableEntity;
import com.example.springbootbackend.repositories.OrderItemRepository;
import com.example.springbootbackend.repositories.OrderRepository;
import com.example.springbootbackend.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final TableRepository tableRepository;
    private final FirebaseMessagingService firebaseMessagingService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void sendOrder(Order order) {
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
    }

    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        ArrayList<OrderItem> orderItems = orderItemRepository.findByOrder(order);
        orderItemRepository.deleteAll(orderItems);
        orderRepository.delete(order);
        TableEntity table = order.getTable();
        table.setStatus("AVAILABLE");
        tableRepository.save(table);
    }

    public void updateOrderToReady(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setStatus(OrderStatus.READY);
        orderRepository.save(order);
        firebaseMessagingService.sendNotificationByToken(orderId);
    }

    public void updateOrderToServed(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.SERVED);
        orderRepository.save(order);
    }

    public void updateOrderToPaid(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }


}
