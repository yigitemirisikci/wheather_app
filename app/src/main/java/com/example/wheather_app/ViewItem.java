package com.example.wheather_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ViewItem extends Fragment {
    String city;
    String temp;
    String url;

    TextView cityTV;
    TextView degreeTV;
    ImageView icon;

    public void getValues(String city,String temp,String url){
        this.city = city;
        this.temp = temp;
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_item,container,false);

        cityTV = view.findViewById(R.id.cityTV);
        degreeTV = view.findViewById(R.id.degreeTV);
        //icon = view.findViewById(R.id.imageView3);

        return view;
    }

    public String getCity() {
        return city;
    }

    public String getTemp() {
        return temp;
    }

    public String getUrl() {
        return url;
    }

    public TextView getCityTV() {
        return cityTV;
    }

    public TextView getDegreeTV() {
        return degreeTV;
    }

    public ImageView getIcon() {
        return icon;
    }
    /*
    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView).load(url).into(imageView);
    }

     */
}
