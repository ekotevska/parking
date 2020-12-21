package com.example.lab.view.activities;

import android.os.Bundle;

import com.example.lab.R;
import com.example.lab.model.database.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivty extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database =  new Database(this);

        EditText username= findViewById(R.id.register_username);
        EditText password= findViewById(R.id.register_password);
        EditText confirm= findViewById(R.id.register_password_confirm);
        Button registracija= findViewById(R.id.register_register);

        registracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(confirm.getText().toString())) {
                    database.insertUser(username.getText().toString(), password.getText().toString());
                    
                    //TODO Make the database insert return if the insertion is complete
                    Toast.makeText(RegisterActivty.this, "Registracijata e uspesna!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivty.this, "Passwordot ne se sovpagja!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}