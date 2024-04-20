package com.example.madp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class loginDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String REG_DB_NAME = "ownerLogin";
    private static final String REG_TABLE_NAME = "users";

    private static final String REG_ID = "id";
    private static final String REG_FULLNAME = "full_name";
    private static final String REG_ADDRESS = "Address";
    private static final String REG_CONTACT = "Contact";
    public static final String REG_EMAIL = "e_mail";
    private static final String REG_USERNAME = "username";
    private static final String REG_PASSWORD = "password";

    public loginDBHelper(Context context) {
        super(context, REG_DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + REG_TABLE_NAME + " (" +
                REG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                REG_FULLNAME + " TEXT," +
                REG_ADDRESS + " TEXT," +
                REG_CONTACT + " TEXT," +
                REG_EMAIL + " TEXT," +
                REG_USERNAME + " TEXT," +
                REG_PASSWORD + " TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public boolean insertUsers(String full_name, String Address, String Contact, String e_mail, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REG_FULLNAME, full_name);
        contentValues.put(REG_ADDRESS, Address);
        contentValues.put(REG_CONTACT, Contact);
        contentValues.put(REG_EMAIL, e_mail);
        contentValues.put(REG_USERNAME, username);
        contentValues.put(REG_PASSWORD, password);
        long result = db.insert(REG_TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public String getPetNameForUser(Context context,String petName) {
        PetDBHelper petDatabaseHelper = new PetDBHelper(context);
        SQLiteDatabase petDatabase = petDatabaseHelper.getReadableDatabase();

        Cursor cursor = petDatabase.query("PetTable", new String[]{"pet_info"}, "pet_name = ?", new String[]{petName}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String petInfo = cursor.getString(0);
            cursor.close();
            petDatabase.close(); // Close the database
            return petInfo;
        } else {
            Log.d("loginDBHelper", "No pet found for pet name: " + petName);
            if (cursor != null) {
                cursor.close();
            }
            petDatabase.close();
        }
        return null;
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REG_TABLE_NAME + " WHERE " + REG_USERNAME + "=? AND " + REG_PASSWORD + "=?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}