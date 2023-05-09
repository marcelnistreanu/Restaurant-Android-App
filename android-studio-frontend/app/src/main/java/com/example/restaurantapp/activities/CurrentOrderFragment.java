package com.example.restaurantapp.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.restaurantapp.CurrentOrderViewModel;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.OrderItem;
import com.example.restaurantapp.recyclerview.CurrentOrderCardAdapter;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.services.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrentOrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView orderRecyclerView;
    private Button sendOrderButton;
    private CurrentOrderCardAdapter orderAdapter;
    private CurrentOrderViewModel currentOrderViewModel;

    private ArrayList<OrderItem> orderItems;
    private ApiService apiService;

    public CurrentOrderFragment() {
    }

    public static CurrentOrderFragment newInstance(String param1, String param2) {
        CurrentOrderFragment fragment = new CurrentOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrderViewModel = new ViewModelProvider(requireActivity()).get(CurrentOrderViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);

        dispaySelectedItems(view);

        return view;
    }

    private void dispaySelectedItems(View view) {
        orderRecyclerView = view.findViewById(R.id.recyclerView2);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderAdapter = new CurrentOrderCardAdapter(getContext(), new ArrayList<FoodItem>(), currentOrderViewModel);
        orderRecyclerView.setAdapter(orderAdapter);

        setupSendOrderButton(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentOrderViewModel.getSelectedItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<FoodItem>>() {
            @Override
            public void onChanged(ArrayList<FoodItem> foodItems) {
                orderAdapter.setSelectedItems(foodItems);
            }
        });
    }

    private void setupSendOrderButton(View view) {
        sendOrderButton = view.findViewById(R.id.btnSendOrder);
        ArrayList<FoodItem> selectedItems = currentOrderViewModel.getSelectedItems().getValue();
        if (selectedItems != null) {
            Order order = new Order();
            order.setStatus("IN PREPARATION");
            orderItems = currentOrderViewModel.createOrder(selectedItems, order);
//            order.setOrderItems(orderItems);
            Log.d("Order items", "orderItems: " + orderItems);
            Log.d("Order", "order: " + order);
            sendOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    performSendOrder(order);
                }
            });
        }


    }

    private void performSendOrder(Order order) {
        apiService = RetrofitService.getApiService();
        Call<Void> call = apiService.sendOrder(order);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Order sent to kitchen", Toast.LENGTH_SHORT).show();
                    currentOrderViewModel.getSelectedItems().getValue().clear();
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to send", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Order failed", "order failed to send: " + t.getMessage());
                Toast.makeText(getContext(), "Failed to send", Toast.LENGTH_SHORT).show();
            }
        });
    }
}