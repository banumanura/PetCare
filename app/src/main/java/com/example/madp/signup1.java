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
public class signup1 extends AppCompatActivity {
    EditText fullname, address, contact, email;
    AppCompatButton next;
    loginDBHelper loginHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup1_activity);
        fullname = findViewById(R.id.editTextTextPersonName2);
        address = findViewById(R.id.editTextTextPersonName4);
        contact = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextPersonName3);
        next = findViewById(R.id.button);
        loginHelper = new loginDBHelper(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = fullname.getText().toString();
                String Address = address.getText().toString();
                String Contact = contact.getText().toString();
                String e_mail = email.getText().toString();

                if (TextUtils.isEmpty(full_name) || TextUtils.isEmpty(Address) || TextUtils.isEmpty(Contact) || TextUtils.isEmpty(e_mail)){
                    Toast.makeText(signup1.this,"All Fields Required", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(getApplicationContext(), signup2.class);
                    intent.putExtra("full_name", full_name);
                    intent.putExtra("Address", Address);
                    intent.putExtra("Contact", Contact);
                    intent.putExtra("e_mail", e_mail);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}