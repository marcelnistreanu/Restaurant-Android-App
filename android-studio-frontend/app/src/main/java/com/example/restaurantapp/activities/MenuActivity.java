package com.example.restaurantapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.restaurantapp.MyAdapter;
import com.example.restaurantapp.R;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.services.RetrofitService;
import com.example.restaurantapp.services.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private MyAdapter myAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        myRecyclerView = findViewById(R.id.recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        apiService = RetrofitService.getApiService();

        Call<ArrayList<FoodItem>> call = apiService.getAllFoodItems();

        call.enqueue(new Callback<ArrayList<FoodItem>>() {
            @Override
            public void onResponse(Call<ArrayList<FoodItem>> call, Response<ArrayList<FoodItem>> response) {
                if(response.isSuccessful()){
                    ArrayList<FoodItem> foodItemList = response.body();
                    myAdapter = new MyAdapter(MenuActivity.this, foodItemList);
                    myRecyclerView.setAdapter(myAdapter);
                    Log.d("Food item list", foodItemList.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FoodItem>> call, Throwable t) {
                Log.e("API call", "Failed to retrieve food item: " + t.getMessage());
            }
        });

    }

    private ArrayList<FoodItem> getMyList(){
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        FoodItem foodItem = new FoodItem(1L, "Pizzuska", "Bla bla", 29, 1000, null, null, null);

        foodItems.add(foodItem);
        return foodItems;
    }

}