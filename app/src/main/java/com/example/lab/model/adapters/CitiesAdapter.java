package com.example.lab.model.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.view.activities.ReservationActivity;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesHolder> {
    private LayoutInflater mInflater;
    private List<String> mData;


    public CitiesAdapter(Context context, ArrayList<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        int images[];
        Log.e("TAG", "" + mData.size());
    }

    @NonNull
    @Override
    public CitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_recycler_cities, parent, false);
        return new CitiesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesHolder holder, int position) {
        holder.imeGrad.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class CitiesHolder extends RecyclerView.ViewHolder {
        private TextView imeGrad;
        private Button rezervirajKopce;
        private ImageView img;

        public CitiesHolder(@NonNull View itemView) {
            super(itemView);
            imeGrad = itemView.findViewById(R.id.view_recycler_grad);
            img=itemView.findViewById(R.id.picture);
            rezervirajKopce = itemView.findViewById(R.id.view_recycler_rezerviraj);
            rezervirajKopce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ReservationActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }



}





