package com.example.restaurantapp.retrofit;

import com.example.restaurantapp.user.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static UserService userService;

    public static UserService getUserService(){
        if(userService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.102:9090")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            userService = retrofit.create(UserService.class);
        }

        return userService;
    }


}
