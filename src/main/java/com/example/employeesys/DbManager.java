package com.example.employeesys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


// DbManager class to manage database.
public class DbManager extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "ManagementSystem.db";
    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String EMPLOYEE_COLUMN_ID = "id";
    public static final String EMPLOYEE_COLUMN_NAME = "name";
    public static final String EMPLOYEE_COLUMN_EMAIL = "email";
    public static final String EMPLOYEE_COLUMN_NUMBER = "number";
    String arrid[], arrname[];


    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table employee( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, email TEXT NOT NULL, number TEXT NOT NULL)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);

    }

    //insert into database
    public String insertEmployee(String name, String email, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("number", number);
        Long res = db.insert("employee", null, contentValues);
        if (res == 1)
            return "failed";
        else
            return "Employee added";
    }

    //get a specific record based on the unique ID
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + EMPLOYEE_TABLE_NAME + " where " + EMPLOYEE_COLUMN_ID + "= " + id, null);
        return res;
    }

    //delete a record
    public int removeEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(" " + EMPLOYEE_TABLE_NAME, "id = ? ", new String[]{Integer.toString(id)});
    }


    //get all records
    public void getAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + EMPLOYEE_TABLE_NAME, null);
        arrid = new String[res.getCount()];
        arrname = new String[res.getCount()];
        res.moveToFirst();

        for (int i = 0; i < arrid.length; i++) {
            arrid[i] = res.getString(0);
            arrname[i] = res.getString(1);
            res.moveToNext();
        }


    }

}
