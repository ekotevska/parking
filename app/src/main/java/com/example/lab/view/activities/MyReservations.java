package com.example.lab.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lab.R;
import com.example.lab.model.adapters.MyReservationsAdapter;
import com.example.lab.model.database.Database;

public class MyReservations extends AppCompatActivity {
    RecyclerView my_reservations;
    MyReservationsAdapter mAdapter;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);
        database = new Database(this);
        my_reservations = (RecyclerView) findViewById(R.id.recycler_reservations);
        my_reservations.setHasFixedSize(true);
        my_reservations.setLayoutManager(new LinearLayoutManager(this));
        my_reservations.setItemAnimator(new DefaultItemAnimator());
        Log.i("GOLEMINA1", "" + database.getMyReservations().size());
        mAdapter = new MyReservationsAdapter(database.getMyReservations(), R.layout.view_my_reservations, this);
        my_reservations.setAdapter(mAdapter);
    }
}
