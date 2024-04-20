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

public class login_activity_c extends AppCompatActivity {

    EditText email, password;
    TextView signup;
    AppCompatButton login;
    caretakerDBHelper caretakerDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_c);

        email = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonName1);

        login = findViewById(R.id.button);
        signup = findViewById(R.id.textView7);

        caretakerDBHelper = new caretakerDBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(login_activity_c.this,"All Fields Required", Toast.LENGTH_SHORT).show();

                } else {
                    boolean check = caretakerDBHelper.checkCaretakerUsernamePassword(user, pass);

                    if (check){
                        Toast.makeText(login_activity_c.this,"Login Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(login_activity_c.this,"Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup1_c.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
