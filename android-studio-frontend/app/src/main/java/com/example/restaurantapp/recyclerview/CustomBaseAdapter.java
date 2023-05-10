package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.OrderItem;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<OrderItem> orderItems;
    LayoutInflater inflater;
    TextView orderItemName;
    LinearLayout linearLayout;

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
        orderItemName = view.findViewById(R.id.orderItemNameTextView);
        orderItemName.setText(orderItems.get(i).getFoodItem().getFoodName());

//        linearLayout = view.findViewById(R.id.linear_layout);
//        int currentHeight = linearLayout.getHeight();
//        int newHeight = currentHeight + (dpToPx(100) * orderItems.size());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, newHeight);
//        linearLayout.setLayoutParams(params);

        return view;
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
