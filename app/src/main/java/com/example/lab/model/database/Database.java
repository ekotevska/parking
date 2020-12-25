package com.example.lab .model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.lab.R;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private Context mContext;
    private ParkingDbHelper dbHelper;
    private SQLiteDatabase database;

    public Database(Context context) {
        this.mContext = context;
        dbHelper = new ParkingDbHelper(context); 
    }


    public void insertUser(String username, String password) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.COLUMN_NAME_USERNAME, username);
        values.put(DatabaseContract.User.COLUMN_NAME_PASSWORD, password);

// Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(DatabaseContract.User.TABLE_NAME, null, values);
    }

    public boolean checkUser(String username, String password) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.User.COLUMN_NAME_USERNAME,
                DatabaseContract.User.COLUMN_NAME_PASSWORD
        };

        String selection = DatabaseContract.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = { username };

        Cursor cursor = database.query(
                DatabaseContract.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.COLUMN_NAME_PASSWORD));
            if(pass.equals(password))
                return true;
            else
                return false;
        }
        return false;
    }
    public void CityDetails() {
        database=dbHelper.getReadableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Скопје");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.skopje);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Охрид");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.ohrid);
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY, "Битола");
        cv.put(DatabaseContract.Cities.COLUMN_NAME_CITY_IMAGE, R.drawable.bitola);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(DatabaseContract.Cities.TABLE_NAME, null, cv);
        database.close();
    }


    }




