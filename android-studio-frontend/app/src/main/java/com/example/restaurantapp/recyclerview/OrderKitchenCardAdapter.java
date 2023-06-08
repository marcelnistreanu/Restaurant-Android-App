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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderKitchenCardAdapter extends RecyclerView.Adapter<OrderKitchenCardHolder> {

    Context context;
    ArrayList<Order> orderList;

    public OrderKitchenCardAdapter(Context context, ArrayList<Order> orderList) {
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
    public OrderKitchenCardHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_orders_kitchen, null);


        return new OrderKitchenCardHolder(view);
    }

    private ArrayList<Order> filterOrderListByStatus() {
        ArrayList<Order> list = new ArrayList<>();
        for (Order order : this.orderList) {
            if (order.getStatus().equals("PREPARING")) {
                list.add(order);
            }
        }

        return list;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderKitchenCardHolder holder, int position) {
        ArrayList<Order> filteredOrderList = filterOrderListByStatus();

        Order order = filteredOrderList.get(position);
        holder.orderId.setText(order.getId().toString());

        CustomKitchenBaseAdapter kitchenBaseAdapter = new CustomKitchenBaseAdapter(getContext(), order.getOrderItems());
        holder.kitchenListView.setAdapter(kitchenBaseAdapter);

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

        holder.readyOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReadyOrder(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterOrderListByStatus().size();
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void setCardSize(@NonNull OrderKitchenCardHolder holder, Order order) {
        int currentHeightList = dpToPx(30);
        Log.d("currentHeightList", "currentHeightList: " + currentHeightList);
        int newHeightList = currentHeightList + (dpToPx(40) * order.getOrderItems().size());
        Log.d("newHeightList", "newHeightList: " + newHeightList);
        ViewGroup.LayoutParams layoutParamsList = holder.kitchenListView.getLayoutParams();
        layoutParamsList.height = newHeightList;
        holder.kitchenListView.setLayoutParams(layoutParamsList);


        int currentHeightExpandableLayout = dpToPx(10);
        int newHeightExpandableLayout = newHeightList + currentHeightExpandableLayout;
        ViewGroup.LayoutParams layoutParamsExpandableLayout = holder.expandableLayout.getLayoutParams();
        layoutParamsExpandableLayout.height = newHeightExpandableLayout;
        holder.expandableLayout.setLayoutParams(layoutParamsExpandableLayout);

        int currentHeightLayout = dpToPx(143);
        int newHeightLayout = newHeightExpandableLayout + currentHeightLayout;
        ViewGroup.LayoutParams layoutParamsLayout = holder.ordersKitchenConstraintLayout.getLayoutParams();
        layoutParamsLayout.height = newHeightLayout;
        holder.ordersKitchenConstraintLayout.setLayoutParams(layoutParamsLayout);

        ViewGroup.LayoutParams layoutParamsCardView = holder.ordersKitchenCardView.getLayoutParams();
        if (layoutParamsCardView == null) {
            layoutParamsCardView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int newHeight = dpToPx(17) + newHeightLayout;
        layoutParamsCardView.height = newHeight;
        holder.ordersKitchenCardView.setLayoutParams(layoutParamsCardView);
    }

    private void setDefaultCardSize(@NonNull OrderKitchenCardHolder holder, Order order) {
        int defaultHeightList = holder.kitchenListView.getHeight();
        ViewGroup.LayoutParams layoutParamsList = holder.kitchenListView.getLayoutParams();
        layoutParamsList.height = defaultHeightList;
        holder.kitchenListView.setLayoutParams(layoutParamsList);


        int defaultHeightExpandableLayout = holder.expandableLayout.getHeight();
        ViewGroup.LayoutParams layoutParamsExpandableLayout = holder.expandableLayout.getLayoutParams();
        layoutParamsExpandableLayout.height = defaultHeightExpandableLayout;
        holder.expandableLayout.setLayoutParams(layoutParamsExpandableLayout);

        int defaultHeightLayout = holder.ordersKitchenConstraintLayout.getHeight();
        ViewGroup.LayoutParams layoutParamsLayout = holder.ordersKitchenConstraintLayout.getLayoutParams();
        layoutParamsLayout.height = dpToPx(143);
        holder.ordersKitchenConstraintLayout.setLayoutParams(layoutParamsLayout);

        ViewGroup.LayoutParams layoutParamsCardView = holder.ordersKitchenCardView.getLayoutParams();
        if (layoutParamsCardView == null) {
            layoutParamsCardView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int defaultHeight = defaultHeightLayout;
        layoutParamsCardView.height = dpToPx(160);
        holder.ordersKitchenCardView.setLayoutParams(layoutParamsCardView);
    }

    private void sendReadyOrder(OrderKitchenCardHolder holder) {
        int position = holder.getAbsoluteAdapterPosition();
        ApiService apiService = RetrofitService.getApiService();
        Call<Void> call = apiService.sendReadyOrder(orderList.get(position).getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Order sent to the waiter", Toast.LENGTH_SHORT).show();
                    orderList.remove(position);
                    notifyDataSetChanged();
                } else {
                    Log.d("API failed", " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("API", "api failed: " + t.getMessage());
            }
        });
    }
}
