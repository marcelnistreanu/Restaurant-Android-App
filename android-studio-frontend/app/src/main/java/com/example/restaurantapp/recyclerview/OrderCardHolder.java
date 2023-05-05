package com.example.restaurantapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.R;

public class OrderCardHolder extends RecyclerView.ViewHolder {

    TextView foodNameTextView, foodPriceTextView;

    public OrderCardHolder(@NonNull View itemView) {
        super(itemView);

        this.foodNameTextView = itemView.findViewById(R.id.foodName);
        this.foodPriceTextView = itemView.findViewById(R.id.foodPrice);
    }
}
