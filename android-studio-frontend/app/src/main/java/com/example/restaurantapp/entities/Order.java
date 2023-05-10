package com.example.restaurantapp.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private Long id;

    private ArrayList<OrderItem> orderItems;

    private Date orderDate;

    private String notes;

    private String status;

    private double totalAmount;

    public Order(Long id, ArrayList<OrderItem> orderItems, Date orderDate, String notes, String status, double totalAmount) {
        this.id = id;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.notes = notes;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
