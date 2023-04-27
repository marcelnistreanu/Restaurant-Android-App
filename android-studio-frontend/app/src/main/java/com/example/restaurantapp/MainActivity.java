package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantapp.retrofit.RetrofitService;
import com.example.restaurantapp.user.AuthResponse;
import com.example.restaurantapp.user.LoginRequest;
import com.example.restaurantapp.user.User;
import com.example.restaurantapp.user.UserService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText editId, editPassword;
    Button btnLogin;
    UserService userService;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLoginButton();

    }

    private void performLogin() {
        userService = RetrofitService.getUserService();
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        editId = findViewById(R.id.editTextId);
        editPassword = findViewById(R.id.editTextPassword);

        String loginCode = String.valueOf(editId.getText());
        String password = String.valueOf(editPassword.getText());

        LoginRequest loginRequest = new LoginRequest(loginCode, password);

        userService.login(loginRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (TextUtils.isEmpty(loginCode)) {
                    Toast.makeText(MainActivity.this, "Enter Login code", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.isSuccessful()) {
                        String accessToken = response.body().getAccessToken();
                        String refreshToken = response.body().getRefreshToken();
                        sharedPreferences.edit().putString("access_token", accessToken).apply();
                        sharedPreferences.edit().putString("refresh_token", refreshToken).apply();

                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupLoginButton() {
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

    }
}