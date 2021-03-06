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
import com.example.wheather_app.R;
import com.squareup.picasso.Picasso;

public class FragmentA extends Fragment {
    public String loc;
    private String status;
    private String temp;
    private String wind;
    private String pressure;
    private String visibility;
    private String humidity;
    private String icon;

    private TextView locTV;
    private TextView statusTV;
    private TextView tempTV;
    private TextView windTV;
    private TextView visibilityTV;
    private TextView humidityTV;
    private TextView pressureTV;
    private ImageView image;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a,container,false);
        locTV = view.findViewById(R.id.locTV);
        statusTV = view.findViewById(R.id.statusTV);
        tempTV = view.findViewById(R.id.tempTV);
        windTV = view.findViewById(R.id.windTV);
        visibilityTV = view.findViewById(R.id.visibilityTV);
        humidityTV = view.findViewById(R.id.humidityTV);
        pressureTV = view.findViewById(R.id.pressureTV);
        image = view.findViewById(R.id.imageView);

        locTV.setText(loc);
        statusTV.setText(status);
        tempTV.setText(temp);
        windTV.setText(wind);
        visibilityTV.setText(visibility);
        humidityTV.setText(humidity);
        pressureTV.setText(pressure);

        Picasso.get().load(icon).into(image);

        return view;
    }

    public void getValues(String loc, String status, String temp, String wind,String icon,
                          String pressure,String humidity,String visibility) {
        this.loc = loc;
        this.status = status;
        this.temp = temp;
        this.wind = wind;
        this.icon = icon;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
    }

    public String getLoc() {
        return loc;
    }

    public String getStatus() {
        return status;
    }

    public String getTemp() {
        return temp;
    }

    public String getWind() {
        return wind;
    }

    public String getIcon(){return icon;}

    public String getPressure() {
        return pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getHumidity() {
        return humidity;
    }


    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView).load(url).into(imageView);
    }

}
