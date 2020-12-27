package com.example.lab.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lab.model.database.DatabaseContract.User;
import com.example.lab.model.database.DatabaseContract.Cities;

import static com.example.lab.model.database.DatabaseContract.*;

public class ParkingDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + " (" +
                    User._ID + " INTEGER PRIMARY KEY," +
                    User.COLUMN_NAME_USERNAME + " TEXT," +
                    User.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String CREATE_TABLE_CITIES = "CREATE TABLE " + Cities.TABLE_NAME + "(" +
            Cities.COLUMN_NAME_CITY_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," + Cities.COLUMN_NAME_CITY + " TEXT,"
            + Cities.COLUMN_NAME_CITY_IMAGE + " INTEGER"+")";


    private static final String CREATE_TABLE_PARKING = "CREATE TABLE " + Parkings.TABLE_NAME + "(" +
            Parkings.COLUMN_NAME_PARKING_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,"
            + Parkings.COLUMN_NAME_CITY_NAME + " TEXT,"
            + Parkings.COLUMN_NAME_PARKING_NAME + " TEXT,"
            + Parkings.COLUMN_NAME_PARKING_LATITUDE + " TEXT,"
            + Parkings.COLUMN_NAME_PARKING_LONGITUDE + " TEXT,"
            + Parkings.COLUMN_NAME_TOTAL_MESTA + " INTEGER" + ")";

    private static final String CREATE_TABLE_RESERVATIONS = "CREATE TABLE " + Reservations.TABLE_NAME + "(" +
            Reservations.COLUMN_NAME_RESERVATION_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,"
            + Reservations.COLUMN_NAME_USER_NAME + " TEXT,"
            + Reservations.COLUMN_NAME_PARKING_ID + " INTEGER,"
            + Reservations.COLUMN_NAME_PARKING_NAME + " TEXT,"
            + Reservations.COLUMN_NAME_CITY + " TEXT,"
            + Reservations.COLUMN_NAME_RESERVATION_TIMESLOT + " TEXT" + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES1 =
            "DROP TABLE IF EXISTS " + Cities.TABLE_NAME;

    public ParkingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(CREATE_TABLE_CITIES);
        db.execSQL(CREATE_TABLE_PARKING);
        db.execSQL(CREATE_TABLE_RESERVATIONS);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
        db.execSQL(SQL_DELETE_ENTRIES1);
        onCreate(db);

    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}