package com.example.restaurantapp.entities;

public class OrderItem {

    private Long id;

    private FoodItem foodItem;

    private Order order;

    private Integer quantity;

    public OrderItem(Long id, FoodItem foodItem, Order order, Integer quantity) {
        this.id = id;
        this.foodItem = foodItem;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderItem(FoodItem foodItem, Integer quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", foodItem=" + foodItem +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}
