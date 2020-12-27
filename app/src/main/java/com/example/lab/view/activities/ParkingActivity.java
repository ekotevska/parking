package com.example.lab.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        mCityName = getIntent().getStringExtra(Constants.EXTRA_STRING_CITY_NAME);
        mTimeslot = getIntent().getStringExtra(Constants.EXTRA_STRING_TIMESLOT);

        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setImeGrad(mCityName);
        rezervacija.setTimeslot(mTimeslot);
        rezervacija.setImeKorisnik("Emka-Bemka");


        mDatabase = new Database(this);
        mParkingRecycler = findViewById(R.id.activity_parking_recycler);
        mParkingRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ParkingAdapter(this, mDatabase.getParkings(mCityName, mTimeslot), rezervacija);
        mParkingRecycler.setAdapter(mAdapter);


    }
}
