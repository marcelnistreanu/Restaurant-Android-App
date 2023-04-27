package com.example.restaurantapp.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/v1/auth/authenticate")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);
}
