package com.example.lab.model.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.model.Constants;
import com.example.lab.model.pojo.City;
import com.example.lab.view.activities.ReservationActivity;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesHolder> {
    private LayoutInflater mInflater;
    private List<City> mData;
    private int rowLayout;
    private Context mContext;
    int images[];


    public CitiesAdapter(Context context, ArrayList<City> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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
        City currCity = mData.get(position);
        holder.imeGrad.setText(currCity.getName());
        holder.img.setImageResource(currCity.getImage());
        holder.rezervirajKopce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReservationActivity.class);
                intent.putExtra(Constants.EXTRA_STRING_CITY_NAME, currCity.getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class CitiesHolder extends RecyclerView.ViewHolder {
        public TextView imeGrad;
        public ImageButton rezervirajKopce;
        public ImageView img;

        public CitiesHolder(@NonNull View itemView) {
            super(itemView);
            imeGrad = (TextView) itemView.findViewById(R.id.view_recycler_grad);
            img=(ImageView) itemView.findViewById(R.id.picture);
            rezervirajKopce =(ImageButton) itemView.findViewById(R.id.view_recycler_rezerviraj);

        }
    }
    public CitiesAdapter(ArrayList<City> mData, int rowLayout, Context context, int[] images) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.images = images;
    }


}





