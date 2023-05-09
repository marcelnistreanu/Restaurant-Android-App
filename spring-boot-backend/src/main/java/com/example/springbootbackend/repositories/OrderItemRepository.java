package com.example.springbootbackend.repositories;

import com.example.springbootbackend.entities.Order;
import com.example.springbootbackend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    ArrayList<OrderItem> findByOrder(Order order);
}
