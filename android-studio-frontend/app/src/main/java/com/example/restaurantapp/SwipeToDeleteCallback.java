package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.recyclerview.OrderWaiterCardAdapter;
import org.jetbrains.annotations.NotNull;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private OrderWaiterCardAdapter mAdapter;

    public SwipeToDeleteCallback(OrderWaiterCardAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.deleteItem(position);
    }
}
