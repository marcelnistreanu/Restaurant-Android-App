package com.example.restaurantapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import org.jetbrains.annotations.NotNull;

public class OrderKitchenCardHolder extends RecyclerView.ViewHolder {

    TextView orderId;
    LinearLayout expandButton, readyOrderButton;
    CardView ordersKitchenCardView;
    ImageView arrowImage;
    ConstraintLayout expandableLayout, ordersKitchenConstraintLayout;
    ListView kitchenListView;

    public OrderKitchenCardHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        orderId = itemView.findViewById(R.id.order_id_kitchen_text_view);
        expandButton = itemView.findViewById(R.id.arrow_button_kitchen);
        ordersKitchenCardView = itemView.findViewById(R.id.orders_kitchen_card_view);
        arrowImage = itemView.findViewById(R.id.arrow_image_kitchen);
        expandableLayout = itemView.findViewById(R.id.expandable_layout_kitchen);
        ordersKitchenConstraintLayout = itemView.findViewById(R.id.order_kitchen_constraint_layout);
        kitchenListView = itemView.findViewById(R.id.kitchen_list_View);
        readyOrderButton = itemView.findViewById(R.id.ready_order_button);
    }
}
