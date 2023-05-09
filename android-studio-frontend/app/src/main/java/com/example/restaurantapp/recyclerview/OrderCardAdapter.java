package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.Order;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderCardAdapter extends RecyclerView.Adapter<OrderCardHolder> {

    Context context;
    ArrayList<Order> orderList;

    public OrderCardAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @NotNull
    @Override
    public OrderCardHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_orders, null);


        return new OrderCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderCardHolder holder, int position) {
        Order order = orderList.get(position);

        holder.orderId.setText(order.getId().toString());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
