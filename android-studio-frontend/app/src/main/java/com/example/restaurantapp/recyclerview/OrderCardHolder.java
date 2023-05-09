package com.example.restaurantapp.recyclerview;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import org.jetbrains.annotations.NotNull;

public class OrderCardHolder extends RecyclerView.ViewHolder {

    TextView orderId;

    public OrderCardHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.orderId = itemView.findViewById(R.id.orderIdTextView);
    }
}
