package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.OrderItem;

import java.util.ArrayList;

public class CustomKitchenBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<OrderItem> orderItems;
    LayoutInflater inflater;
    TextView orderItemName;

    public CustomKitchenBaseAdapter(Context context, ArrayList<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
        this.inflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.row_order_item_kitchen, null);
        orderItemName = view.findViewById(R.id.order_item_kitchen_text_view);
        orderItemName.setText(orderItems.get(i).getFoodItem().getFoodName());

        return view;
    }
}
