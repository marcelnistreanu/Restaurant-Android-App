package com.example.restaurantapp.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static ApiService apiService;

    public static ApiService getApiService(){
        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.102:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }


}
