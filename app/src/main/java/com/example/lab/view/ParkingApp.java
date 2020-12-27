package com.example.lab.view;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.lab.model.database.Database;

public class ParkingApp extends Application {

    Database database;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("TAG", "Application is running");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("TAG", "Application is running for the first time");
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            database = new Database(this);
            database.insertCities();
            database.insertParkings();

            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        }
    }
    }
