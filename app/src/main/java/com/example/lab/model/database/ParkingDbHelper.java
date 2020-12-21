package com.example.lab.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lab.model.database.DatabaseContract.Parking;
import com.example.lab.model.database.DatabaseContract.User;

public class ParkingDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + " (" +
                    User._ID + " INTEGER PRIMARY KEY," +
                    User.COLUMN_NAME_USERNAME + " TEXT," +
                    User.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String CREATE_TABLE1 = "CREATE TABLE " + Parking.TABLE_NAME + "(" +
            Parking.COLUMN_NAME_CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Parking.COLUMN_NAME_CITY + " TEXT,"
            + Parking.COLUMN_NAME_CITY_IMAGE + " INTEGER"+")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;

    public ParkingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(CREATE_TABLE1);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}