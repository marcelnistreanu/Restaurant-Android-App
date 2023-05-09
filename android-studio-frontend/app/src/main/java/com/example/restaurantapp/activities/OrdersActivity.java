package com.example.restaurantapp.activities;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.recyclerview.OrderCardAdapter;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.restaurantapp.R.id.orderRecyclerView;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView orderRecyclerView;
    private OrderCardAdapter orderCardAdapter;
    private ApiService apiService;
    private ArrayList<Order> ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadOrderList();
    }

    private void loadOrderList() {
        apiService = RetrofitService.getApiService();
        Call<ArrayList<Order>> call = apiService.getAllOrders();

        call.enqueue(new Callback<ArrayList<Order>>() {
            @Override
            public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                if(response.isSuccessful()) {
                    ordersList = response.body();
                    orderCardAdapter = new OrderCardAdapter(getApplicationContext(), ordersList);
                    orderRecyclerView.setAdapter(orderCardAdapter);
                    Log.d("Order list", "orderList: " + ordersList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Order>> call, Throwable t) {
                Log.d("API", "Failed to retrieve order list: " + t.getMessage());
            }
        });
    }

}