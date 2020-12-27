package com.example.lab.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.adapters.CitiesAdapter;
import com.example.lab.model.database.Database;

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
        database = new Database(this);

        cities = (RecyclerView) findViewById(R.id.recycler_cities);
        cities.setHasFixedSize(true);
        cities.setLayoutManager(new LinearLayoutManager(this));
        cities.setItemAnimator(new DefaultItemAnimator());
        Log.i("GOLEMINA", "" + database.getCities().size());
        mAdapter = new CitiesAdapter(database.getCities(), R.layout.view_recycler_cities, this, image_id);
        cities.setAdapter(mAdapter);
    }
   /* public static String getUsername() {
        return username;
    }
*/
       //cities = findViewById(R.id.recycler_cities);
       //cities.setLayoutManager(new LinearLayoutManager(this));
       //CitiesAdapter adapter = new CitiesAdapter(this, gradovi);
       //cities.setAdapter(adapter);


        //db=new Database(this);
        //TODO: povrzuvanje so db
        //gradovi.add("Skopje");
        //gradovi.add("Kumanovo");
        //gradovi.add("Bitola");
        //gradovi.add("Resen");
        //gradovi.add("Kicevo");
        //gradovi.add("Krusevo");
        //gradovi.add("Tetovo");
        //gradovi.add("Veles");
        //gradovi.add("Prilep");


}
