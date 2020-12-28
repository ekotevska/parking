package com.example.lab.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.action_favorite) {
                Intent myReservationsIntent = new Intent(ConfirmationActivity.this, MyReservations.class);
                startActivity(myReservationsIntent);
            }
            return true;
        });

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
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(this, "Otvori moi rezervacii activity", Toast.LENGTH_SHORT).show();
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}