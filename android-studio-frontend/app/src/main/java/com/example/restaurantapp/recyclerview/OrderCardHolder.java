package com.example.restaurantapp.recyclerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import org.jetbrains.annotations.NotNull;

public class OrderCardHolder extends RecyclerView.ViewHolder {

    TextView orderId, status;
    LinearLayout statusBackground;
    ListView orderItemsListView;
    CardView ordersCardView;
    ConstraintLayout orderConstraintLayout;

    ConstraintLayout expandableLayout;

    public OrderCardHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.orderId = itemView.findViewById(R.id.orderIdTextView);
        this.status = itemView.findViewById(R.id.statusTextView);
        this.statusBackground = itemView.findViewById(R.id.layout_status);
        this.orderItemsListView = itemView.findViewById(R.id.listView);
        this.ordersCardView = itemView.findViewById(R.id.ordersCardView);
        this.orderConstraintLayout = itemView.findViewById(R.id.orderConstraintLayout);
        this.expandableLayout = itemView.findViewById(R.id.exapandableLayout);
    }
}
