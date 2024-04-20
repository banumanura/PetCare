package com.example.madp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class caretakerDBHelper extends SQLiteOpenHelper {
    Context context;
    private static final int VERSION = 1;
    private static final String CARETAKER_DB_NAME = "caretakerLogin";
    private static final String CARETAKER_TABLE_NAME = "caretakers";


    private static final String CARETAKER_ID = "id";
    private static final String CARETAKER_FULLNAME = "full_name";
    private static final String CARETAKER_ADDRESS = "Address";
    private static final String CARETAKER_CONTACT = "Contact";
    private static final String CARETAKER_EMAIL = "e_mail";
    private static final String CARETAKER_USERNAME = "username";
    private static final String CARETAKER_PASSWORD = "password";

    public caretakerDBHelper(@Nullable Context context) {
        super(context, CARETAKER_DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + CARETAKER_TABLE_NAME + " " +
                "(" +
                CARETAKER_ID + " INTEGER PRIMARY KEY " + "AUTOINCREMENT," +
                CARETAKER_FULLNAME + " TEXT," +
                CARETAKER_ADDRESS + " TEXT," +
                CARETAKER_CONTACT + " TEXT," +
                CARETAKER_EMAIL + " TEXT," +
                CARETAKER_USERNAME + " TEXT," +
                CARETAKER_PASSWORD + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean insertCaretaker(String full_name, String Address, String Contact, String e_mail, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARETAKER_FULLNAME, full_name);
        contentValues.put(CARETAKER_ADDRESS, Address);
        contentValues.put(CARETAKER_CONTACT, Contact);
        contentValues.put(CARETAKER_EMAIL, e_mail);
        contentValues.put(CARETAKER_USERNAME, username);
        contentValues.put(CARETAKER_PASSWORD, password);
        long result = db.insert(CARETAKER_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkCaretakerUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM caretakers WHERE username=? AND password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}