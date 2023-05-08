package com.example.springbootbackend.repositories;

import com.example.springbootbackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
