package com.example.restaurantapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantapp.R;
import com.example.restaurantapp.services.RetrofitService;
import com.example.restaurantapp.auth.AuthResponse;
import com.example.restaurantapp.auth.AuthRequest;
import com.example.restaurantapp.services.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;
    ApiService apiService;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupLoginButton();
        retrieveDeviceToken();

    }

    private void performLogin() {
        apiService = RetrofitService.getApiService();
        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

        editEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editTextPassword);

        String email = String.valueOf(editEmail.getText());
        String password = String.valueOf(editPassword.getText());

        AuthRequest authRequest = new AuthRequest(email, password);

        apiService.authenticate(authRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                int statusCode = response.code();
                Log.d("RESPONSE_CODE", "Response code: " + statusCode);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.isSuccessful()) {
                        String accessToken = response.body().getAccessToken();
                        String refreshToken = response.body().getRefreshToken();
                        sharedPreferences.edit().putString("access_token", accessToken).apply();
                        sharedPreferences.edit().putString("refresh_token", refreshToken).apply();

                        Log.d("TOKENS", "Access token: " + accessToken);
                        Log.d("TOKENS", "Refresh token: " + refreshToken);
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        if (response.body().getRole().equals("WAITER")) {
                            Intent intent = new Intent(getApplicationContext(), DashboardWaiterActivity.class);
                            startActivity(intent);
                        } else if (response.body().getRole().equals("CHEF")) {
                            Intent intent = new Intent(getApplicationContext(), DashboardKitchenActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        String message = "";
                        try {
                            message = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (message.contains("Error: Email not found.")) {
                            Toast.makeText(LoginActivity.this, "Error: Email not found.", Toast.LENGTH_SHORT).show();
                        } else if (message.contains("Error: Invalid password.")) {
                            Toast.makeText(LoginActivity.this, "Error: Invalid password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void retrieveDeviceToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.d("TAG", "Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d("Device token", token);
                    }
                });
    }
}