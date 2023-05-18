package com.example.restaurantapp.activities;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.restaurantapp.R;

public class DashboardKitchenActivity extends AppCompatActivity {

    private TextView ordersKitchenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_kitchen);

        setupOrdersKitchenButon();
    }

    public void setupOrdersKitchenButon() {
        ordersKitchenButton = findViewById(R.id.btnOrdersChef);
        ordersKitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrdersKitchenActivity.class);
                startActivity(intent);
            }
        });
    }
}