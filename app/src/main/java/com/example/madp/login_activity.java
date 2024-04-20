package com.example.madp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_activity extends AppCompatActivity {

    EditText email, password;
    TextView signup;
    AppCompatButton login;
    loginDBHelper loginHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonName1);
        login = findViewById(R.id.button);
        signup = findViewById(R.id.textView7);
        loginHelper = new loginDBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(login_activity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = loginHelper.checkUsernamePassword(user, pass);
                    if (check) {
                        Toast.makeText(login_activity.this, "Login Success!", Toast.LENGTH_SHORT).show();

                        //In here I've modified getPetNameForUser method by adding one additional context parameter
                        String petName = loginHelper.getPetNameForUser(getApplicationContext(),user); // Fetch pet name
                        Intent intent = new Intent(getApplicationContext(), PetDetailsActivity.class);
                        intent.putExtra("petIdentifier", petName); // Pass pet name to PetDetailsActivity
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(login_activity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
