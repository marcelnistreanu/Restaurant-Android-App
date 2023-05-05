package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;

import java.util.ArrayList;

public class OrderCardAdapter extends RecyclerView.Adapter<OrderCardHolder>{

    Context context;
    ArrayList<FoodItem> selectedItems;

    public OrderCardAdapter(Context context, ArrayList<FoodItem> selectedItems) {
        this.context = context;
        this.selectedItems = selectedItems;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<FoodItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<FoodItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @NonNull
    @Override
    public OrderCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);


        return new OrderCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCardHolder holder, int position) {
        FoodItem foodItem = selectedItems.get(position);

        holder.foodNameTextView.setText(foodItem.getFoodName());
        holder.foodPriceTextView.setText(String.valueOf(foodItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return selectedItems.size();
    }
}
