package com.example.madp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_main extends AppCompatActivity {
    AppCompatButton ownerLogin;
    AppCompatButton careTakerLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ownerLogin = findViewById(R.id.button81);
        careTakerLogin = findViewById(R.id.button88);

        ownerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_main.this,login_activity.class));
            }
        });
        careTakerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_main.this,login_activity_c.class));
            }
        });
    }
}
