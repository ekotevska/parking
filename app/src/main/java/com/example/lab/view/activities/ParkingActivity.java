package com.example.lab.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.example.lab.model.adapters.ParkingAdapter;
import com.example.lab.model.database.Database;
import com.example.lab.model.pojo.Rezervacija;

public class ParkingActivity extends AppCompatActivity {

    RecyclerView mParkingRecycler;
    ParkingAdapter mAdapter;
    Database mDatabase;
    String mCityName;
    String mTimeslot;
    String mUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.action_favorite) {
                Intent myReservationsIntent = new Intent(ParkingActivity.this, MyReservations.class);
                startActivity(myReservationsIntent);
            }
            return true;
        });

        SharedPreferences prefs = getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString(Constants.EXTRA_STRING_USER_NAME, "guest");

        mCityName = getIntent().getStringExtra(Constants.EXTRA_STRING_CITY_NAME);
        mTimeslot = getIntent().getStringExtra(Constants.EXTRA_STRING_TIMESLOT);

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setImeGrad(mCityName);
        rezervacija.setTimeslot(mTimeslot);
        rezervacija.setImeKorisnik(name);


        mDatabase = new Database(this);
        mParkingRecycler = findViewById(R.id.activity_parking_recycler);
        mParkingRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ParkingAdapter(this, mDatabase.getParkings(mCityName, mTimeslot), rezervacija);
        mParkingRecycler.setAdapter(mAdapter);


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
