package com.example.restaurantapp.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.recyclerview.FoodCardAdapter;

import java.util.ArrayList;

public class CurrentOrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView orderRecyclerView;

    private FoodCardAdapter orderAdapter;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);

        orderRecyclerView = view.findViewById(R.id.recyclerView2);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderAdapter = new FoodCardAdapter(getContext(), loadFoodList());
        orderRecyclerView.setAdapter(orderAdapter);

        return view;
    }

    private ArrayList<FoodItem> loadFoodList() {
        ArrayList<FoodItem> selectedItems = new ArrayList<>();
        selectedItems.add(new FoodItem("Pizza", 20));
        selectedItems.add(new FoodItem("Mayo", 5));
        selectedItems.add(new FoodItem("Cheburek", 2430));

        return selectedItems;
    }
}