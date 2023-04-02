package com.example.restaurantapp.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/v1/users/login")
    Call<Void> login(@Body LoginRequest loginRequest);
}
