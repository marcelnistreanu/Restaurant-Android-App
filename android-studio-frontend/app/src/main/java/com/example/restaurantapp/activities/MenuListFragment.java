package com.example.restaurantapp.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurantapp.CurrentOrderViewModel;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.recyclerview.FoodCardAdapter;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.services.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView myRecyclerView;
    private FoodCardAdapter myAdapter;
    private ApiService apiService;

    private CurrentOrderViewModel currentOrderViewModel;


    public MenuListFragment() {
    }

    public static MenuListFragment newInstance(String param1, String param2) {
        MenuListFragment fragment = new MenuListFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        myRecyclerView = view.findViewById(R.id.recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadFoodList();


        return view;
    }

    private void loadFoodList() {
        apiService = RetrofitService.getApiService();
        Call<ArrayList<FoodItem>> call = apiService.getAllFoodItems();

        call.enqueue(new Callback<ArrayList<FoodItem>>() {
            @Override
            public void onResponse(Call<ArrayList<FoodItem>> call, Response<ArrayList<FoodItem>> response) {
                if (response.isSuccessful()) {
                    ArrayList<FoodItem> foodItemList = response.body();
                    myAdapter = new FoodCardAdapter(getContext(), foodItemList, currentOrderViewModel);
                    myRecyclerView.setAdapter(myAdapter);
                    Log.d("Food item list", foodItemList.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FoodItem>> call, Throwable t) {
                Log.e("API call", "Failed to retrieve food item list: " + t.getMessage());
            }
        });
    }
}