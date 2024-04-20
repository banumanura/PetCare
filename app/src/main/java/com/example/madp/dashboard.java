package com.example.madp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {

    Button logoutButton;
    Button catButton;
    Button dogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        logoutButton = findViewById(R.id.button9);
        catButton = findViewById(R.id.buttonc);
        dogButton = findViewById(R.id.buttond);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, activity_main.class);
                startActivity(intent);
                finish();
            }
        });

        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, PetDetailsC.class);
                startActivity(intent);
                finish();
            }
        });

        dogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, PetDetailsC.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
