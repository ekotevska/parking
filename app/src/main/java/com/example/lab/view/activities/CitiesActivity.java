package com.example.lab.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.adapters.CitiesAdapter;
import com.example.lab.model.database.Database;
import com.example.lab.model.database.DatabaseContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {
    RecyclerView cities;
    CitiesAdapter mAdapter;
    Database database;

   // public static String username;
    int image_id[] ={R.drawable.skopje, R.drawable.ohrid, R.drawable.bitola};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.action_favorite) {
                Toast.makeText(this, "Otvori moi rezervacii activity", Toast.LENGTH_SHORT).show();
                Intent myReservationsIntent = new Intent(CitiesActivity.this, MyReservations.class);
                startActivity(myReservationsIntent);
            }
            return true;
        });

        database = new Database(this);

        cities = (RecyclerView) findViewById(R.id.recycler_cities);
        cities.setHasFixedSize(true);
        cities.setLayoutManager(new LinearLayoutManager(this));
        cities.setItemAnimator(new DefaultItemAnimator());
        Log.i("GOLEMINA", "" + database.getCities().size());
        mAdapter = new CitiesAdapter(database.getCities(), R.layout.view_recycler_cities, this, image_id);
        cities.setAdapter(mAdapter);
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
