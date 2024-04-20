package com.example.madp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup2 extends AppCompatActivity {
    EditText animal, petName, petAge, petBreed, petGender, petColor, petInfo;
    AppCompatButton next;
    loginDBHelper loginHelper;
    PetDBHelper petDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2_activity);

        String full_name = getIntent().getStringExtra("full_name");
        String Address = getIntent().getStringExtra("Address");
        String Contact = getIntent().getStringExtra("Contact");
        String e_mail = getIntent().getStringExtra("e_mail");

        animal = findViewById(R.id.editTextTextPersonName5);
        petName = findViewById(R.id.editTextTextPersonName6);
        petAge = findViewById(R.id.editTextTextPersonName7);
        petBreed = findViewById(R.id.editTextTextPersonName1);
        petGender = findViewById(R.id.editTextTextPersonName2);
        petColor = findViewById(R.id.editTextTextPersonName3);
        petInfo = findViewById(R.id.editTextTextPersonName4);
        next = findViewById(R.id.button);

        loginHelper = new loginDBHelper(this);
        petDBHelper = new PetDBHelper(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aniMal = animal.getText().toString();
                String pet_name = petName.getText().toString();
                String pet_age = petAge.getText().toString();
                String pet_breed = petBreed.getText().toString();
                String pet_gender = petGender.getText().toString();
                String pet_color = petColor.getText().toString();
                String pet_info = petInfo.getText().toString();

                if (TextUtils.isEmpty(aniMal) || TextUtils.isEmpty(pet_name) || TextUtils.isEmpty(pet_age) || TextUtils.isEmpty(pet_breed) || TextUtils.isEmpty(pet_gender) || TextUtils.isEmpty(pet_color) || TextUtils.isEmpty(pet_info)) {
                    Toast.makeText(signup2.this,"All Fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insertPet = petDBHelper.insertPet(aniMal, pet_name, pet_age, pet_breed, pet_gender, pet_color, pet_info);

                    if (insertPet) {
                        Intent intent = new Intent(getApplicationContext(), signup3.class);
                        intent.putExtra("full_name", full_name);
                        intent.putExtra("Address", Address);
                        intent.putExtra("Contact", Contact);
                        intent.putExtra("e_mail", e_mail);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(signup2.this, "Failed to insert pet data!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
