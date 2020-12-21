package com.example.lab.view.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.adapters.CitiesAdapter;
import com.example.lab.model.database.Database;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {
    Database db;
    RecyclerView cities;
    ArrayList<String> gradovi = new ArrayList<>();
    int image_id[] ={R.drawable.skopje, R.drawable.ohrid, R.drawable.bitola};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        db=new Database(this);
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

        cities = findViewById(R.id.recycler_cities);
        cities.setLayoutManager(new LinearLayoutManager(this));
        CitiesAdapter adapter = new CitiesAdapter(this, gradovi);
        cities.setAdapter(adapter);




    }
}
