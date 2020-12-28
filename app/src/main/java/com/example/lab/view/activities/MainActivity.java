package com.example.lab.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.example.lab.model.database.Database;


public class MainActivity extends AppCompatActivity {


    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        database = new Database(this);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivty.class);
                MainActivity.this.startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CitiesActivity.class);


                if (database.checkUser(username.getText().toString(), password.getText().toString())) {
                    MainActivity.this.startActivity(intent);
                    SharedPreferences.Editor editor = getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(Constants.EXTRA_STRING_USER_NAME, username.getText().toString());
                    editor.apply();

                } else {
                    Toast.makeText(MainActivity.this, "Username ili Password se pogresni!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}








