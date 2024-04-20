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
public class signup3_c extends AppCompatActivity {
    EditText username, password, rPassword;
    AppCompatButton signup;
    caretakerDBHelper loginHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup3_activity_c);

        String full_name = getIntent().getStringExtra("full_name");
        String Address = getIntent().getStringExtra("Address");
        String Contact = getIntent().getStringExtra("Contact");
        String e_mail = getIntent().getStringExtra("e_mail");

        username = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPersonName4);
        rPassword = findViewById(R.id.editTextTextPersonName);
        signup = findViewById(R.id.button);
        loginHelper = new caretakerDBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String passWord = password.getText().toString();
                String r_passWord = rPassword.getText().toString();

                if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(passWord) || TextUtils.isEmpty(r_passWord)){
                    Toast.makeText(signup3_c.this,"All Fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insert = loginHelper.insertCaretaker(full_name, Address, Contact, e_mail, user_name, passWord);
                    if (insert){
                        Intent intent = new Intent(getApplicationContext(),login_activity_c.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(signup3_c.this,"Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}