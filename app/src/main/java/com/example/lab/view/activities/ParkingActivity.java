package com.example.lab.view.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.adapters.CitiesAdapter;

import java.util.ArrayList;

public class ParkingActivity extends AppCompatActivity {

    RecyclerView parkinzi;
    ArrayList<String> places = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        places.add(" ");
        places.add(" ");


        parkinzi = findViewById(R.id.parking_places);
        parkinzi.setLayoutManager(new LinearLayoutManager(this));
        CitiesAdapter adapter = new CitiesAdapter(this, places);
        parkinzi.setAdapter(adapter);




    }
}
