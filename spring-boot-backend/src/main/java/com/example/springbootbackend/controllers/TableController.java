package com.example.springbootbackend.controllers;

import com.example.springbootbackend.entities.TableEntity;
import com.example.springbootbackend.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class TableController {

    private final TableRepository tableRepository;

    @GetMapping("/getTablesList")
    public ResponseEntity<?> getTablesList() {
        return ResponseEntity.ok(tableRepository.findAll());
    }

}
