package com.example.springbootbackend.controllers;

import com.example.springbootbackend.services.FirebaseMessagingService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class DeviceTokenController {

    private final FirebaseMessagingService firebaseMessagingService;

    @PutMapping("/device-token")
    public ResponseEntity<String> updateDeviceToken(@RequestBody String newDeviceToken) {
        try {
            firebaseMessagingService.updateDeviceToken(newDeviceToken);
            System.out.println(newDeviceToken);
            return ResponseEntity.ok(newDeviceToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to get device token");
        }
    }
}
