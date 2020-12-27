package com.example.lab.view.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    String mCityName;
    Button mPickdate, mReserve;
    Spinner mSpinner;
    DatePicked listener;
    int mDay= 0, mMonth = 0, mYear = 1999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mCityName = getIntent().getStringExtra(Constants.EXTRA_STRING_CITY_NAME);
        Toast.makeText(this, "City name: " + mCityName, Toast.LENGTH_SHORT).show();

        mSpinner = findViewById(R.id.reseervation_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timeslots, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mPickdate = findViewById(R.id.reservation_date_pick);
        listener = new DatePicked() {
            @Override
            public void updateDate(int day, int month, int year) {

                mDay = day;
                mMonth = month + 1;
                mYear = year;
                mPickdate.setText(mDay + "." + mMonth + "." + mYear);
                Log.i("DATUM", "Datumot e: " + mMonth);
            }
        };
        mPickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment(listener);
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        mReserve = findViewById(R.id.reservation_okay);
        mReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationActivity.this, ParkingActivity.class);
                intent.putExtra(Constants.EXTRA_STRING_CITY_NAME, mCityName);
                intent.putExtra(Constants.EXTRA_STRING_TIMESLOT, mYear + "-"+mMonth+"-"+mDay+"-"+ mSpinner.getSelectedItem().toString());
                ReservationActivity.this.startActivity(intent);
            }
        });

    }


    interface DatePicked {
         void updateDate(int day, int month,int year);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        DatePicked listener;

        DatePickerFragment(DatePicked listener) {
            this.listener = listener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            listener.updateDate(dayOfMonth, month, year);
        }
    }


}
