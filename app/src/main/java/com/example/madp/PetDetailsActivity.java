package com.example.madp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PetDetailsActivity extends AppCompatActivity {

    private PetDBHelper dbHelper;
    private TextView petType, petName, petAge, petBreed, petGender, petColor, petInfo;
    private EditText noOfDays, date;
    private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_details);

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
        noOfDays = findViewById(R.id.editTextTextPersonName);
        date = findViewById(R.id.editTextTextPersonName3);
        postButton = findViewById(R.id.button);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePetDetails();
                Intent intent = new Intent(PetDetailsActivity.this, dashboard_o.class);
                startActivity(intent);
                Toast.makeText(PetDetailsActivity.this, "You posted your pet ad", Toast.LENGTH_SHORT).show();
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

    private void updatePetDetails() {
        String petNameValue = petName.getText().toString();
        String noOfDaysValue = noOfDays.getText().toString();
        String dateValue = date.getText().toString();

        ContentValues values = new ContentValues();
        values.put(PetDBHelper.COLUMN_NO_OF_DAYS, noOfDaysValue);
        values.put(PetDBHelper.COLUMN_DATE, dateValue);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(PetDBHelper.TABLE_DETAILS, values, PetDBHelper.COLUMN_PET_NAME + "=?", new String[]{petNameValue});
    }
}
