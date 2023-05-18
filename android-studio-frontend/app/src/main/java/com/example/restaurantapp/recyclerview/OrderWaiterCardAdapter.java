package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.services.RetrofitService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class OrderWaiterCardAdapter extends RecyclerView.Adapter<OrderWaiterCardHolder> {

    Context context;
    ArrayList<Order> orderList;

    public OrderWaiterCardAdapter(Context context, ArrayList<Order> orderList) {
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
    public OrderWaiterCardHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_orders_waiter, null);


        return new OrderWaiterCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderWaiterCardHolder holder, int position) {
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

        CustomWaiterBaseAdapter orderItemListAdapter = new CustomWaiterBaseAdapter(getContext(), order.getOrderItems());
        holder.orderItemsListView.setAdapter(orderItemListAdapter);

        setDefaultCardSize(holder, order);

        holder.expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!order.isExpanded()) {
                    setCardSize(holder, order);
                    holder.arrowImage.setImageResource(R.drawable.arrow_up_24);
                    order.setExpanded(true);
                    Log.d("Card", "Card expanded");
                    holder.expandableLayout.setVisibility(View.VISIBLE);
                } else {
                    setDefaultCardSize(holder, order);
                    holder.arrowImage.setImageResource(R.drawable.arrow_down_24);
                    order.setExpanded(false);
                    Log.d("Card", "Card collapsed");
                    holder.expandableLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void setCardSize(@NonNull OrderWaiterCardHolder holder, Order order) {
        int currentHeightList = dpToPx(30);
        Log.d("currentHeightList", "currentHeightList: " + currentHeightList);
        int newHeightList = currentHeightList + (dpToPx(40) * order.getOrderItems().size());
        Log.d("newHeightList", "newHeightList: " + newHeightList);
        ViewGroup.LayoutParams layoutParamsList = holder.orderItemsListView.getLayoutParams();
        layoutParamsList.height = newHeightList;
        holder.orderItemsListView.setLayoutParams(layoutParamsList);


        int currentHeightExpandableLayout = dpToPx(10);
        int newHeightExpandableLayout = newHeightList + currentHeightExpandableLayout;
        ViewGroup.LayoutParams layoutParamsExpandableLayout = holder.expandableLayout.getLayoutParams();
        layoutParamsExpandableLayout.height = newHeightExpandableLayout;
        holder.expandableLayout.setLayoutParams(layoutParamsExpandableLayout);

        int currentHeightLayout = dpToPx(100);
        int newHeightLayout = newHeightExpandableLayout + currentHeightLayout;
        ViewGroup.LayoutParams layoutParamsLayout = holder.orderConstraintLayout.getLayoutParams();
        layoutParamsLayout.height = newHeightLayout;
        holder.orderConstraintLayout.setLayoutParams(layoutParamsLayout);

        ViewGroup.LayoutParams layoutParamsCardView = holder.ordersCardView.getLayoutParams();
        if (layoutParamsCardView == null) {
            layoutParamsCardView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int newHeight = dpToPx(17) + newHeightLayout;
        layoutParamsCardView.height = newHeight;
        holder.ordersCardView.setLayoutParams(layoutParamsCardView);
    }

    private void setDefaultCardSize(@NonNull OrderWaiterCardHolder holder, Order order) {
        int defaultHeightList = holder.orderItemsListView.getHeight();
        ViewGroup.LayoutParams layoutParamsList = holder.orderItemsListView.getLayoutParams();
        layoutParamsList.height = defaultHeightList;
        holder.orderItemsListView.setLayoutParams(layoutParamsList);


        int defaultHeightExpandableLayout = holder.expandableLayout.getHeight();
        ViewGroup.LayoutParams layoutParamsExpandableLayout = holder.expandableLayout.getLayoutParams();
        layoutParamsExpandableLayout.height = defaultHeightExpandableLayout;
        holder.expandableLayout.setLayoutParams(layoutParamsExpandableLayout);

        int defaultHeightLayout = holder.orderConstraintLayout.getHeight();
        ViewGroup.LayoutParams layoutParamsLayout = holder.orderConstraintLayout.getLayoutParams();
        layoutParamsLayout.height = dpToPx(100);
        holder.orderConstraintLayout.setLayoutParams(layoutParamsLayout);

        ViewGroup.LayoutParams layoutParamsCardView = holder.ordersCardView.getLayoutParams();
        if (layoutParamsCardView == null) {
            layoutParamsCardView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int defaultHeight = defaultHeightLayout;
        layoutParamsCardView.height = dpToPx(117);
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

    public void deleteItem(int position) {
        ApiService apiService = RetrofitService.getApiService();
        Call<Void> call = apiService.deleteOrder(orderList.get(position).getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    orderList.remove(position);
                    Log.d("API", "Order deleted successfully");
                    Toast.makeText(getContext(), "Order deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("API", "Failed to delete order: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("API", "Failed to delete: " + t.getMessage());
            }
        });
        notifyItemRemoved(position);
    }
}
