package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.retrofit.RetrofitService;
import com.example.restaurantapp.user.ApiService;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    private TextView foodNameTextView, foodPriceTextView;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        foodNameTextView = findViewById(R.id.foodName);
        foodPriceTextView = findViewById(R.id.foodPrice);

        apiService = RetrofitService.getApiService();

        String foodName = "Pepperoni";

        Call<FoodItem> call = apiService.getFoodItem(foodName);

        call.enqueue(new Callback<FoodItem>() {
            @Override
            public void onResponse(Call<FoodItem> call, Response<FoodItem> response) {
                if(response.isSuccessful()){
                    FoodItem foodItem = response.body();
                    Log.d("FoodItem", foodItem.toString());
                    foodNameTextView.setText(foodItem.getFoodName());
                    foodPriceTextView.setText(String.valueOf(foodItem.getPrice()) + " LEI");
                } else {
                    Toast.makeText(MenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodItem> call, Throwable t) {
                Log.e("API call", "Failed to retrieve food item: " + t.getMessage());
            }
        });

    }
}