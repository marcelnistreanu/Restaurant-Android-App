package com.example.restaurantapp.entities;

import java.time.LocalDate;

public class TableEntity {

    private Long id;

    private int capacity;

    private String description;

    private String status;

    private LocalDate updatedAt;

    private Order order;

    public TableEntity(Long id, int capacity, String description, String status, LocalDate updatedAt, Order order) {
        this.id = id;
        this.capacity = capacity;
        this.description = description;
        this.status = status;
        this.updatedAt = updatedAt;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Order getTableOrder() {
        return order;
    }

    public void setTableOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", updatedAt=" + updatedAt +
                ", order=" + order +
                '}';
    }
}
