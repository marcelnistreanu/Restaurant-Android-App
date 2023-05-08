package com.example.restaurantapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.OrderItem;

import java.util.ArrayList;

public class CurrentOrderViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<FoodItem>> selectedItems = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<OrderItem>> orderItems = new MutableLiveData<>();


    public MutableLiveData<ArrayList<FoodItem>> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<FoodItem> foodItems) {
        selectedItems.setValue(foodItems);
    }

//    public void setOrderItems(ArrayList<OrderItem> orderItems) {
//        this.orderItems.setValue(orderItems);
//    }

    public MutableLiveData<ArrayList<OrderItem>> getOrderItems() {
        return orderItems;
    }

    public ArrayList<OrderItem> createOrder(ArrayList<FoodItem> selectedItems, Order order) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (FoodItem foodItem : selectedItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFoodItem(foodItem);
            orderItem.setQuantity(1);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        this.orderItems.setValue(orderItems);
        return orderItems;
    }
}
