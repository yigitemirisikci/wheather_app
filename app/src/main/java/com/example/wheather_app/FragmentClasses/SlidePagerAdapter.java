package com.example.wheather_app.FragmentClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheather_app.R;

import java.net.URL;
import java.util.ArrayList;

public class SlidePagerAdapter extends RecyclerView.Adapter<SlidePagerAdapter.ViewHolder> {
    private ArrayList<FragmentA> fragmentList;

    @NonNull
    @Override
    public SlidePagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_a,parent,false);
        return new ViewHolder(view);
    }

    public SlidePagerAdapter(ArrayList<FragmentA> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Override
    public void onBindViewHolder(@NonNull SlidePagerAdapter.ViewHolder holder, int position) {
        FragmentA fragmentA = fragmentList.get(position);

        holder.locTV.setText(fragmentA.getLoc());
        holder.tempTV.setText(fragmentA.getTemp());
        holder.statusTV.setText(fragmentA.getStatus());
        holder.windTV.setText(fragmentA.getWind());
        holder.timeTV.setText(fragmentA.getTime());
        holder.visibilityTV.setText(fragmentA.getVisibility());
        holder.pressureTV.setText(fragmentA.getPressure());
        holder.humidityTV.setText(fragmentA.getHumidity());

        try {
            URL url = new URL("https:"+fragmentA.getIcon());
            System.out.println("url ok");
            Bitmap mIcon_val = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            System.out.println("bitmap ok");
            holder.imageView.setImageBitmap(mIcon_val);
            System.out.println("all ok");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView locTV;
        TextView timeTV;
        TextView statusTV;
        TextView tempTV;
        TextView windTV;
        TextView visibilityTV;
        TextView humidityTV;
        TextView pressureTV;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            locTV = itemView.findViewById(R.id.locTV);
            timeTV = itemView.findViewById(R.id.pressureTV);
            statusTV = itemView.findViewById(R.id.statusTV);
            tempTV = itemView.findViewById(R.id.tempTV);
            windTV = itemView.findViewById(R.id.windTV);
            pressureTV = itemView.findViewById(R.id.pressureTV);
            humidityTV = itemView.findViewById(R.id.humidityTV);
            visibilityTV = itemView.findViewById(R.id.visibilityTV);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    public void setFragmentList(ArrayList<FragmentA> fragmentList) {
        this.fragmentList = fragmentList;
    }
}
