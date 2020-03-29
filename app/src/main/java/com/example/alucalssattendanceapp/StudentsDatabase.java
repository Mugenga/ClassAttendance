package com.example.alucalssattendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentsDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cohort1";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "students";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    public StudentsDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( " +
                NAME + "TEXT" +
                EMAIL + "TEXT" +
                PASSWORD + "TEXT" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void trial1(String name, String email, String password) {
        SQLiteDatabase data = getWritableDatabase();

        ContentValues createcontent = new ContentValues();

        createcontent.put(NAME, name);
        createcontent.put(EMAIL, email);
        createcontent.put(PASSWORD, password);

        data.insert(TABLE_NAME, null, createcontent);
    }
}
