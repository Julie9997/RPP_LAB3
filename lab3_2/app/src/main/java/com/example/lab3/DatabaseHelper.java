package com.example.lab3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "lab3", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students ( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "last_name TEXT,"
                + "first_name TEXT,"
                + "middle_name TEXT,"
                + "time TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE students RENAME TO prev_table;");

        db.execSQL("CREATE TABLE students ( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "last_name TEXT,"
                + "first_name TEXT,"
                + "middle_name TEXT,"
                + "time TEXT);");

        Cursor cursor = db.rawQuery("SELECT * FROM prev_table", null);
        cursor.moveToFirst();

        String name;
        do {
            name = cursor.getString(1);
            String[] fio = name.split(" ");

            db.execSQL("INSERT INTO students(_id, first_name, middle_name, last_name, time) " +
                    "VALUES (\'" + cursor.getString(0) + "\',\'" + fio[0] + "\', \'" + fio[1] + "\', \'" + fio[2] + "\', \'" + cursor.getString(2) + "\');");
        } while(cursor.moveToNext());

        cursor.close();

        db.execSQL("DROP TABLE prev_table");
    }
}