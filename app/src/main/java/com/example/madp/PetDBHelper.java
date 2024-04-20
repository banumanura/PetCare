package com.example.madp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PetDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PetDatabase";
    public static final int DATABASE_VERSION = 26;
    public static final String TABLE_NAME = "PetTable";
    public static final String TABLE_DETAILS = "PetDetails";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ANIMAL = "animal";
    public static final String COLUMN_PET_NAME = "pet_name";
    public static final String COLUMN_PET_AGE = "pet_age";
    public static final String COLUMN_PET_BREED = "pet_breed";
    public static final String COLUMN_PET_GENDER = "pet_gender";
    public static final String COLUMN_PET_COLOR = "pet_color";
    public static final String COLUMN_PET_INFO = "pet_info";
    public static final String COLUMN_OWNER_EMAIL = "e_mail";

    public static final String COLUMN_NO_OF_DAYS = "no_of_days";
    public static final String COLUMN_DATE = "date";

    public PetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("PetDBHelper", "Creating new database tables.");
        String createPetTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ANIMAL + " TEXT, " +
                COLUMN_PET_NAME + " TEXT UNIQUE, " + // Make pet_name unique
                COLUMN_PET_AGE + " TEXT, " +
                COLUMN_PET_BREED + " TEXT, " +
                COLUMN_PET_GENDER + " TEXT, " +
                COLUMN_PET_COLOR + " TEXT, " +
                COLUMN_PET_INFO + " TEXT, " +
                COLUMN_OWNER_EMAIL + " TEXT)";
        db.execSQL(createPetTableQuery);

        String createDetailsTableQuery = "CREATE TABLE " + TABLE_DETAILS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PET_NAME + " TEXT, " +
                COLUMN_NO_OF_DAYS + " INTEGER, " +
                COLUMN_DATE + " TEXT)";
        db.execSQL(createDetailsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("PetDBHelper", "Upgrading database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
        onCreate(db);
    }

    public boolean insertPet(String animal, String petName, String petAge, String petBreed, String petGender, String petColor, String petInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ANIMAL, animal);
        contentValues.put(COLUMN_PET_NAME, petName);
        contentValues.put(COLUMN_PET_AGE, petAge);
        contentValues.put(COLUMN_PET_BREED, petBreed);
        contentValues.put(COLUMN_PET_GENDER, petGender);
        contentValues.put(COLUMN_PET_COLOR, petColor);
        contentValues.put(COLUMN_PET_INFO, petInfo);

        try {
            long result = db.insertOrThrow(TABLE_NAME, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            Log.e("PetDBHelper", "Error inserting pet: " + e.getMessage());
            return false;
        }
    }

    public boolean insertPetDetails(String petName, int noOfDays, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PET_NAME, petName);
        contentValues.put(COLUMN_NO_OF_DAYS, noOfDays);
        contentValues.put(COLUMN_DATE, date);

        long result = db.insert(TABLE_DETAILS, null, contentValues);
        return result != -1;
    }
}