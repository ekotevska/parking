package com.example.lab.model.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.example.lab.model.database.Database;
import com.example.lab.model.pojo.Parking;
import com.example.lab.model.pojo.Rezervacija;
import com.example.lab.view.activities.ConfirmationActivity;
import com.example.lab.view.activities.ReservationActivity;

import java.util.ArrayList;
import java.util.List;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingHolder> {
    private final Context mContext;
    Database database;
    private LayoutInflater mInflater;
    private ArrayList<Parking> mData;
    private Rezervacija mRezervacija;


    public ParkingAdapter(Context context, ArrayList<Parking> data, Rezervacija rezervacija) {
        database = new Database(context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mRezervacija = rezervacija;

        Log.e("TAG", "" + mData.size());
    }

    @NonNull
    @Override
    public ParkingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_parking_places, parent, false);
        return new ParkingHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ParkingHolder holder, int position) {
        Parking parking = mData.get(position);
        holder.imeParking.setText(parking.getName());
        holder.zafateni.setText(String.format("%d", parking.getZafateni()));
        holder.slobodni.setText(String.format(" %d", parking.getSlobodni()));
        holder.rezervirajMesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO activity za confirmation
                if (parking.getSlobodni() != 0) {
                    mRezervacija.setImeParking(mData.get(position).getName());
                    if (database.insertReservation(mRezervacija) != -1) {
                        Intent intent = new Intent(mContext, ConfirmationActivity.class);
                        intent.putExtra(Constants.EXTRA_STRING_PARKING_NAME, mRezervacija.getImeParking());
                        mContext.startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "Ne moze da se izvrsi rezervacija. Mrs!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "Nema slobodni mesta.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ParkingHolder extends RecyclerView.ViewHolder {
        private TextView imeParking;
        private TextView slobodni;
        private TextView zafateni;
        private ImageButton rezervirajMesto;

        public ParkingHolder(@NonNull View itemView) {
            super(itemView);
            imeParking = itemView.findViewById(R.id.view_parking_place);
            slobodni = itemView.findViewById(R.id.view_parking_place_slobodni);
            zafateni = itemView.findViewById(R.id.view_parking_place_zafateni);
            rezervirajMesto = itemView.findViewById(R.id.view_parking_place_rezerviraj);


        }
    }


}





