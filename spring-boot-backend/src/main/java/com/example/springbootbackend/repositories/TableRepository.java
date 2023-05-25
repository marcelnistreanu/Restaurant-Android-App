package com.example.springbootbackend.repositories;

import com.example.springbootbackend.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
