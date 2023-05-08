package com.example.restaurantapp.services;

import com.example.restaurantapp.auth.AuthResponse;
import com.example.restaurantapp.auth.AuthRequest;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/v1/auth/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);

    @GET("/api/v1/auth/getUser")
    Call<User> getUser(@Header("Authorization") String token);

    @GET("/api/v1/food/getAll")
    Call <ArrayList<FoodItem>> getAllFoodItems();

    @GET("api/v1/food/getFood/{foodName}")
    Call<FoodItem> getFoodItem(@Path("foodName") String foodName);

    @POST("api/v1/food/sendOrder")
    Call<Void> sendOrder(@Body Order order);
}
