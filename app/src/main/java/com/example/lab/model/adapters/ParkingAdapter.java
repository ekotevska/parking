package com.example.lab.model.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.view.activities.ReservationActivity;

import java.util.ArrayList;
import java.util.List;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingHolder> {
    private LayoutInflater mInflater;
    private List<String> mData;


    public ParkingAdapter(Context context, ArrayList<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        Log.e("TAG", "" + mData.size());
    }

    @NonNull
    @Override
    public ParkingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_parking_places, parent, false);
        return new ParkingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingHolder holder, int position) {
        holder.imeParking.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ParkingHolder extends RecyclerView.ViewHolder {
        private TextView imeParking;
        private TextView slobodni;
        private TextView zafateni;
        private Button rezervirajMesto;

        public ParkingHolder(@NonNull View itemView) {
            super(itemView);
            imeParking = itemView.findViewById(R.id.view_parking_place);
            slobodni=itemView.findViewById(R.id.slobodni_mesta);
            zafateni=itemView.findViewById(R.id.zafateni_mesta);
            rezervirajMesto = itemView.findViewById(R.id.view_parking_place_rezerviraj);
            rezervirajMesto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //TODO activity za confirmation
                    // Intent intent = new Intent(itemView.getContext(), ConfirmationActivity.class);
                   // itemView.getContext().startActivity(intent);
                }
            });

        }
    }



}





