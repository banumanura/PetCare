package com.example.madp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dashboard_o extends AppCompatActivity {

    Button logoutButton;
    Button booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_o);

        logoutButton = findViewById(R.id.button9);
        booking = findViewById(R.id.button81);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard_o.this, activity_main.class);
                startActivity(intent);
                finish();
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard_o.this, PetDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
