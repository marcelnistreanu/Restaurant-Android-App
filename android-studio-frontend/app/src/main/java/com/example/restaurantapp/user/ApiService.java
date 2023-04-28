package com.example.restaurantapp.user;

import com.example.restaurantapp.auth.AuthResponse;
import com.example.restaurantapp.auth.AuthRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/v1/auth/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);

    @GET("/api/v1/auth/getUser")
    Call<User> getUser(@Header("Authorization") String token);
}
