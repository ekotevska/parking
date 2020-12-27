package com.example.lab.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.example.lab.model.database.Database;
import com.example.lab.model.pojo.Parking;

public class ConfirmationActivity extends AppCompatActivity {

    Database database;
    Button mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        database = new Database(this);
        String imeParking = getIntent().getStringExtra(Constants.EXTRA_STRING_PARKING_NAME);

        Location parking = database.getParkingLocation(imeParking);
        Log.i("TAG", "" + parking.getLatitude());

        mNavigation = findViewById(R.id.activity_confirmation_navigation);
        mNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+ parking.getLatitude() + "," + parking.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                //TODO Launch google maps
            }
        });
    }
}