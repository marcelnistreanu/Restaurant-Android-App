package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantapp.retrofit.RetrofitService;
import com.example.restaurantapp.user.LoginRequest;
import com.example.restaurantapp.user.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText editId, editPassword;
    Button btnLogin;

    UserService userService = RetrofitService.getUserService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editId = findViewById(R.id.editTextId);
                editPassword = findViewById(R.id.editTextPassword);

                String loginCode = String.valueOf(editId.getText());
                String password = String.valueOf(editPassword.getText());

                LoginRequest loginRequest = new LoginRequest(loginCode, password);

                Call<Void> call = userService.login(loginRequest);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


}