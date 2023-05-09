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

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<OrderItem> orderItems;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context context, ArrayList<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
        inflater = LayoutInflater.from(context);
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
        view = inflater.inflate(R.layout.row_order_item, null);
        TextView orderItemName = view.findViewById(R.id.orderItemTextView);
        orderItemName.setText(orderItems.get(i).getFoodItem().getFoodName());
        return view;
    }
}
