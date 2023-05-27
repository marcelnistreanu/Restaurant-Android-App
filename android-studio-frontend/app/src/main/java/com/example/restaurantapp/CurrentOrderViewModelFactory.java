package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CurrentOrderViewModelFactory implements ViewModelProvider.Factory{

    private static CurrentOrderViewModel instance;

    public static CurrentOrderViewModel getInstance() {
        if (instance == null) {
            instance = new CurrentOrderViewModel();
        }
        return instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CurrentOrderViewModel.class)) {
            return (T) getInstance();
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
