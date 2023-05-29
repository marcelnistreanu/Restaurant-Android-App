package com.example.restaurantapp.entities;

public enum OrderStatus {

    PREPARING("PREPARING"),
    READY("READY"),
    SERVED("SERVED"),
    PAID("PAID");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
