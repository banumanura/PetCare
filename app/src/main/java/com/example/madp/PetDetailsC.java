package com.example.madp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PetDetailsC extends AppCompatActivity {

    private PetDBHelper dbHelper;
    private TextView petType, petName, petAge, petBreed, petGender, petColor, petInfo, petDays, petDate;
    private Button acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_details_c);

        dbHelper = new PetDBHelper(this);
        initializeViews();
        loadPetData();
    }

    private void initializeViews() {
        petType = findViewById(R.id.pet_type);
        petName = findViewById(R.id.pet_name);
        petAge = findViewById(R.id.pet_age);
        petBreed = findViewById(R.id.pet_breed);
        petGender = findViewById(R.id.pet_gender);
        petColor = findViewById(R.id.pet_color);
        petInfo = findViewById(R.id.pet_info);
        petDays = findViewById(R.id.pet_days);
        petDate = findViewById(R.id.pet_date);
        acceptButton = findViewById(R.id.button);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetDetailsC.this, dashboard.class);
                startActivity(intent);
                Toast.makeText(PetDetailsC.this, "You accepted this pet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPetData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(PetDBHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            petType.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_ANIMAL)));
            petName.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_PET_NAME)));
            petAge.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_PET_AGE)));
            petBreed.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_PET_BREED)));
            petGender.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_PET_GENDER)));
            petColor.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_PET_COLOR)));
            petInfo.setText(cursor.getString(cursor.getColumnIndex(PetDBHelper.COLUMN_PET_INFO)));

            cursor.close();
        }
    }
}
