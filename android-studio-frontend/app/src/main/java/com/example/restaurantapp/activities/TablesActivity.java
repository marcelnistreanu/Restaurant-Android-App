package com.example.restaurantapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.restaurantapp.CurrentOrderViewModel;
import com.example.restaurantapp.CurrentOrderViewModelFactory;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.TableEntity;
import com.example.restaurantapp.recyclerview.TableCardAdapter;
import com.example.restaurantapp.services.ApiService;
import com.example.restaurantapp.services.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TablesActivity extends AppCompatActivity {

    ApiService apiService;

    RecyclerView tablesRecyclerView;
    private TableCardAdapter tableAdapter;
    CurrentOrderViewModel currentOrderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        tablesRecyclerView = findViewById(R.id.recycler_tables);
        tablesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        currentOrderViewModel = new ViewModelProvider(this, new CurrentOrderViewModelFactory()).get(CurrentOrderViewModel.class);
        Log.d("ViewModel Instance", "TablesActivity: " + currentOrderViewModel.hashCode());
        loadTablesList();

    }

    private void loadTablesList() {
        apiService = RetrofitService.getApiService();
        Call<ArrayList<TableEntity>> call = apiService.getTablesList();
//        currentOrderViewModel = new ViewModelProvider(this).get(CurrentOrderViewModel.class);

        call.enqueue(new Callback<ArrayList<TableEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<TableEntity>> call, Response<ArrayList<TableEntity>> response) {
                if (response.isSuccessful()) {
                    ArrayList<TableEntity> tablesList = response.body();
                    tableAdapter = new TableCardAdapter(getApplicationContext(), tablesList, currentOrderViewModel);
                    tablesRecyclerView.setAdapter(tableAdapter);
                    Log.d("Tables list", tablesList.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TableEntity>> call, Throwable t) {
                Log.d("API", "Failed to fetch: " + t.getMessage());
            }
        });
    }
}