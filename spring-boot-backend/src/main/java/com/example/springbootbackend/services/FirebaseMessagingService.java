package com.example.springbootbackend.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    private String deviceToken;

    public void updateDeviceToken(String newDeviceToken) {
        this.deviceToken = newDeviceToken.replace("\"", "");
    }

    public void sendNotificationByToken(Long orderId) {

        Notification notification = Notification
                .builder()
                .setTitle("Restaurant")
                .setBody("Order " + orderId + " is ready!")
                .build();

        Message message = Message
                .builder()
                .setToken(deviceToken)
                .setNotification(notification)
                .build();

        try {
            String response = firebaseMessaging.send(message);
            System.out.println("Successfully sent notification: " + response);
        } catch (FirebaseMessagingException e) {
            System.out.println("Failed to send notification: " + e.getMessage());
        }
    }

}
