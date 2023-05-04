package com.example.restaurantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.entities.FoodItem;

import java.util.ArrayList;

public class FoodCardAdapter extends RecyclerView.Adapter<FoodCardHolder> {

    Context context;
    ArrayList<FoodItem> foodItems;

    public FoodCardAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public FoodCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);


        return new FoodCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCardHolder holder, int position) {

        holder.foodNameTextView.setText(foodItems.get(position).getFoodName());
        holder.foodPriceTextView.setText(String.valueOf(foodItems.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
}
