package com.example.restaurantapp.services;

import com.example.restaurantapp.auth.AuthResponse;
import com.example.restaurantapp.auth.AuthRequest;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.entities.Order;
import com.example.restaurantapp.entities.TableEntity;
import com.example.restaurantapp.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/v1/food/sendOrder")
    Call<Void> sendOrder(@Body Order order);

    @GET("api/v1/food/getAllOrders")
    Call<ArrayList<Order>> getAllOrders();

    @POST("/api/v1/auth/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);

    @GET("/api/v1/auth/getUser")
    Call<User> getUser(@Header("Authorization") String token);

    @GET("/api/v1/food/getAll")
    Call <ArrayList<FoodItem>> getAllFoodItems();

    @GET("api/v1/food/getFood/{foodName}")
    Call<FoodItem> getFoodItem(@Path("foodName") String foodName);


    @DELETE("api/v1/food/deleteOrder/{orderId}")
    Call<Void> deleteOrder(@Path("orderId") Long orderId);

    @PUT("api/v1/food/sendReadyOrder/{orderId}")
    Call<Void> sendReadyOrder(@Path("orderId") Long orderId);

    @PUT("api/v1/food/sendServedOrder/{orderId}")
    Call<Void> sendServedOrder(@Path("orderId") Long orderId);

    @PUT("api/v1/food/sendPaidOrder/{orderId}")
    Call<Void> sendPaidOrder(@Path("orderId") Long orderId);

    @GET("api/v1/food/getTablesList")
    Call<ArrayList<TableEntity>> getTablesList();

    @PUT("api/v1/auth/device-token")
    Call<String> updateDeviceToken(@Body String newDeviceToken);
}
