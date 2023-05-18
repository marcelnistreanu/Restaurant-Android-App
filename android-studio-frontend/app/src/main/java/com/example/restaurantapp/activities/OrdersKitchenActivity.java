package com.example.restaurantapp.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.recyclerview.OrderKitchenCardAdapter;
import com.example.restaurantapp.recyclerview.OrderWaiterCardAdapter;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class OrdersKitchenActivity extends AppCompatActivity {

    private ApiService apiService;
    private ArrayList<Order> orderList;

    private RecyclerView ordersKitchenRecyclerView;
    private OrderKitchenCardAdapter orderKitchenCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_kitchen);

        ordersKitchenRecyclerView = findViewById(R.id.recycler_orders_kitchen);
        ordersKitchenRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadOrderList();
    }

    private void loadOrderList() {
        apiService = RetrofitService.getApiService();
        Call<ArrayList<Order>> call = apiService.getAllOrders();
        call.enqueue(new Callback<ArrayList<Order>>() {
            @Override
            public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                if (response.isSuccessful()) {
                    orderList = response.body();
                    orderKitchenCardAdapter = new OrderKitchenCardAdapter(getApplicationContext(), orderList);
                    ordersKitchenRecyclerView.setAdapter(orderKitchenCardAdapter);
                    Log.d("API", "orderList: " + orderList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Order>> call, Throwable t) {
                Log.d("API", "Api failed: " + t.getMessage());
            }
        });
    }

}