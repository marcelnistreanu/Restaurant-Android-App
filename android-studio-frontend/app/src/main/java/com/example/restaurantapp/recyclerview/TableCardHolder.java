package com.example.restaurantapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.R;

public class TableCardHolder extends RecyclerView.ViewHolder{

    TextView tableIdTextView, tableStatusTextView, orderIdTextView;
    ImageView menuButton;
    LinearLayout statusBackground;

    public TableCardHolder(@NonNull View itemView) {
        super(itemView);

        this.tableIdTextView = itemView.findViewById(R.id.table_id);
        this.tableStatusTextView = itemView.findViewById(R.id.table_status);
        this.orderIdTextView = itemView.findViewById(R.id.table_order_id);
        this.menuButton = itemView.findViewById(R.id.table_menu_button);
        this.statusBackground = itemView.findViewById(R.id.status_background);
    }
}
