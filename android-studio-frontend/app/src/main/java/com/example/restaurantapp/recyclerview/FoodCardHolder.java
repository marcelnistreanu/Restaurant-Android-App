package com.example.restaurantapp.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.R;

public class FoodCardHolder extends RecyclerView.ViewHolder {

    TextView foodNameTextView, foodPriceTextView;
    ImageView addButton;

    public FoodCardHolder(@NonNull View itemView) {
        super(itemView);

        this.foodNameTextView = itemView.findViewById(R.id.foodName);
        this.foodPriceTextView = itemView.findViewById(R.id.foodPrice);
        this.addButton = itemView.findViewById(R.id.btnAdd);
    }
}
