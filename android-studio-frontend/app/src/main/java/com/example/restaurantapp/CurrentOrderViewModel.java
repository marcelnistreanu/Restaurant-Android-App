package com.example.restaurantapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantapp.entities.FoodItem;

import java.util.ArrayList;

public class CurrentOrderViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<FoodItem>> selectedItems = new MutableLiveData<>();

    public MutableLiveData<ArrayList<FoodItem>> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<FoodItem> foodItems) {
        selectedItems.setValue(foodItems);
    }
}
