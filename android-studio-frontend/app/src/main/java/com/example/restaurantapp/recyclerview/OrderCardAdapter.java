package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.activities.OrdersActivity;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.Order;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

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
        holder.status.setText(order.getStatus());
        if (Objects.equals(order.getStatus(), "READY")) {
            holder.statusBackground.setBackgroundResource(R.drawable.bg_status_done);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.status.getLayoutParams();
            int marginStart = dpToPx(40);
            params.setMarginStart(marginStart);
            holder.status.setLayoutParams(params);
        }
        CustomBaseAdapter orderItemListAdapter = new CustomBaseAdapter(getContext(), order.getOrderItems());
        holder.orderItemsListView.setAdapter(orderItemListAdapter);

        setCardSize(holder, order);


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void setCardSize(@NonNull OrderCardHolder holder, Order order) {
        int currentHeightList = dpToPx(40) + holder.orderItemsListView.getHeight();
        Log.d("currentHeightList", "currentHeightList: " + currentHeightList);
        int newHeightList = currentHeightList + (dpToPx(40) * order.getOrderItems().size());
        Log.d("newHeightList", "newHeightList: " + newHeightList);
        ViewGroup.LayoutParams layoutParamsList = holder.orderItemsListView.getLayoutParams();
        layoutParamsList.height = newHeightList;
        holder.orderItemsListView.setLayoutParams(layoutParamsList);

        int currentHeightExpandableLayout = dpToPx(10) + holder.expandableLayout.getHeight();
        int newHeightExpandableLayout = newHeightList + currentHeightExpandableLayout;
        ViewGroup.LayoutParams layoutParamsExpandableLayout = holder.expandableLayout.getLayoutParams();
        layoutParamsExpandableLayout.height = newHeightExpandableLayout;
        holder.expandableLayout.setLayoutParams(layoutParamsExpandableLayout);

        int currentHeightLayout = dpToPx(80) + holder.orderConstraintLayout.getHeight();
        int newHeightLayout = newHeightExpandableLayout + currentHeightLayout;
        ViewGroup.LayoutParams layoutParamsLayout = holder.orderConstraintLayout.getLayoutParams();
        layoutParamsLayout.height = newHeightLayout;
        holder.orderConstraintLayout.setLayoutParams(layoutParamsLayout);

        ViewGroup.LayoutParams layoutParamsCardView = holder.ordersCardView.getLayoutParams();
        if (layoutParamsCardView == null) {
            layoutParamsCardView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int newHeight = dpToPx(15) + newHeightLayout;
        layoutParamsCardView.height = newHeight;
        holder.ordersCardView.setLayoutParams(layoutParamsCardView);
    }

    public void sortOrdersList() {
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getStatus().equals("READY") && !o2.getStatus().equals("READY")) {
                    return -1;
                } else if (!o1.getStatus().equals("READY") && o2.getStatus().equals("READY")) {
                    return 1;
                } else {
                    return Long.compare(o2.getId(), o1.getId());
                }
            }
        });
    }
}
