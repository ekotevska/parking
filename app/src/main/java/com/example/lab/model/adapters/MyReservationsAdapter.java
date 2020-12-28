package com.example.lab.model.adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.example.lab.model.pojo.Rezervacija;
import com.example.lab.view.activities.MyReservations;
import com.example.lab.view.activities.ReservationActivity;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.MyReservationsHolder> {
    private LayoutInflater mInflater;
    private List<Rezervacija> mData;
    private int rowLayout;
    private Context mContext;

    public MyReservationsAdapter(Context context, ArrayList<Rezervacija> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @NonNull
    @Override
    public MyReservationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_my_reservations, parent, false);
        return new MyReservationsHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyReservationsHolder holder, int position) {
        Rezervacija currRes = mData.get(position);
        holder.imeKorisnik.setText(currRes.getImeKorisnik());
        holder.imeParking.setText(currRes.getImeParking());
        holder.imeGrad.setText(currRes.getImeGrad());
        holder.timeslot.setText(currRes.getTimeslot());
    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    public static class MyReservationsHolder extends RecyclerView.ViewHolder {
        public TextView imeKorisnik;
        public TextView imeParking;
        public TextView imeGrad;
        public TextView timeslot;

        public MyReservationsHolder(@NonNull View itemView) {
            super(itemView);
            imeKorisnik = (TextView) itemView.findViewById(R.id.view_ime_korisnik);
            imeGrad= (TextView) itemView.findViewById(R.id.view_ime_grad);
            imeParking = (TextView) itemView.findViewById(R.id.view_ime_parking);
            timeslot=(TextView) itemView.findViewById(R.id.view_timeslot);

        }
    }
    public MyReservationsAdapter(ArrayList<Rezervacija> mData, int rowLayout, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }


}


