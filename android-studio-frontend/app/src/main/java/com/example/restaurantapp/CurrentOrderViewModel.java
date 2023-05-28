package com.example.restaurantapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.OrderItem;
import com.example.restaurantapp.entities.TableEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class CurrentOrderViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<FoodItem>> selectedItems = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<OrderItem>> orderItems = new MutableLiveData<>();
    private final MutableLiveData<TableEntity> selectedTable = new MutableLiveData<>();

    public final MutableLiveData<ArrayList<FoodItem>> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<FoodItem> foodItems) {
        selectedItems.setValue(foodItems);
    }

//    public void setOrderItems(ArrayList<OrderItem> orderItems) {
//        this.orderItems.setValue(orderItems);
//    }

    public final MutableLiveData<TableEntity> getSelectedTable() {
        return selectedTable;
    }

    public void setSelectedTable(TableEntity table) {
        selectedTable.setValue(table);
    }


    public MutableLiveData<ArrayList<OrderItem>> getOrderItems() {
        return orderItems;
    }

    public ArrayList<OrderItem> createOrder(ArrayList<FoodItem> selectedItems, Order order) {
        TableEntity table = getSelectedTable().getValue();
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (FoodItem foodItem : selectedItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFoodItem(foodItem);
            orderItem.setQuantity(1);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTable(table);
//        table.setTableOrder(order);
        this.orderItems.setValue(orderItems);
        return orderItems;
    }
}
