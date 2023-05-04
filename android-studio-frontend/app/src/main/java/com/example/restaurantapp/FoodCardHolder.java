package com.example.restaurantapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodCardHolder extends RecyclerView.ViewHolder {

    TextView foodNameTextView, foodPriceTextView;

    public FoodCardHolder(@NonNull View itemView) {
        super(itemView);

        this.foodNameTextView = itemView.findViewById(R.id.foodName);
        this.foodPriceTextView = itemView.findViewById(R.id.foodPrice);
    }
}
