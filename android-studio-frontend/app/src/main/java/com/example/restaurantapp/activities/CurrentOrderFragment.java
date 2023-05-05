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

import com.example.restaurantapp.BuildConfig;
import com.example.restaurantapp.CurrentOrderViewModel;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.recyclerview.FoodCardAdapter;
import com.example.restaurantapp.recyclerview.OrderCardAdapter;

import java.util.ArrayList;

import timber.log.Timber;

public class CurrentOrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView orderRecyclerView;

    private OrderCardAdapter orderAdapter;

    private CurrentOrderViewModel currentOrderViewModel;

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
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
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

        orderAdapter = new OrderCardAdapter(getContext(), new ArrayList<FoodItem>(), currentOrderViewModel);
        orderRecyclerView.setAdapter(orderAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentOrderViewModel.getSelectedItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<FoodItem>>() {
            @Override
            public void onChanged(ArrayList<FoodItem> foodItems) {
                orderAdapter.setSelectedItems(foodItems);
                Timber.tag("CurrentOrderFragment").d("Selected items changed: %s", foodItems.toString());
            }
        });
    }
}