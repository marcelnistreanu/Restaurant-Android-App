package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.CurrentOrderViewModel;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;

import java.util.ArrayList;

public class FoodCardAdapter extends RecyclerView.Adapter<FoodCardHolder> {

    Context context;
    ArrayList<FoodItem> foodItems;

    static ArrayList<FoodItem> selectedItems = new ArrayList<>();
    CurrentOrderViewModel currentOrderViewModel;


    public FoodCardAdapter(Context context, ArrayList<FoodItem> foodItems, CurrentOrderViewModel currentOrderViewModel) {
        this.context = context;
        this.foodItems = foodItems;
        this.currentOrderViewModel = currentOrderViewModel;
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
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_list, null);


        return new FoodCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCardHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);

        holder.foodNameTextView.setText(foodItem.getFoodName());
        holder.foodPriceTextView.setText(String.valueOf(foodItem.getPrice()) + " LEI");
        holder.addButton.setEnabled(!selectedItems.contains(foodItem));
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems.add(foodItem);
                currentOrderViewModel.setSelectedItems(selectedItems);

                Toast.makeText(getContext(), "Added to current order", Toast.LENGTH_SHORT).show();
                Log.d("Selected items", "Selected items: " + selectedItems.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
}
