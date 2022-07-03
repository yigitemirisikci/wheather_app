package com.example.wheather_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wheather_app.RetrofitClasses.WheatherApi;
import com.example.wheather_app.RetrofitClasses.WheatherTurkey;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements LocationListener {
    //XML ITEMS
    private Button button;
    private EditText editText;

    //RETROFIT
    private Retrofit retrofit;
    private String baseUrl = "https://api.weatherapi.com/v1/";
    private WheatherApi wheatherApi;
    private Call<WheatherTurkey> wheatherTurkeyCall;
    private  WheatherTurkey wheatherTurkey;
    private String baseLocation;

    //FRAGMENT
    private RecyclerView recyclerView;
    private ArrayList<ViewItem> itemList;
    private SlidePagerAdapter slidePagerAdapter;

    private void init(){
        button =findViewById(R.id.button);
        editText = findViewById(R.id.edittext);
        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();
        slidePagerAdapter = new SlidePagerAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(slidePagerAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); //initialize

        //using gps
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getLocation();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setRetrofit(1);
                editText.setText("");
            }
        });
    }

    //mode == 0 -> baseLocation, mode == 1 -> get loc from edittext
    private void setRetrofit(int mode){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        wheatherApi = retrofit.create(WheatherApi.class);

        String city;
        if(mode == 0){
            city = baseLocation;
        }
        else{
            city = editText.getText().toString();
        }

        //If city has shown already -> error
        for(ViewItem v: itemList){
            if(v.getCity().equals(city)){
                Toast.makeText(getApplicationContext(), "City has shown already!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        //If city edittext is empty
        if(!city.equals("")){
            wheatherTurkeyCall = wheatherApi.getWheather(city);
        }
        else {
            Toast.makeText(getApplicationContext(), "City cant be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        //retrofit call
        wheatherTurkeyCall.enqueue(new Callback<WheatherTurkey>() {
            @Override
            public void onResponse(Call<WheatherTurkey> call, Response<WheatherTurkey> response) {
                if (response.isSuccessful()){
                    wheatherTurkey = response.body();
                    if (wheatherTurkey != null)
                    {
                        FragmentA fragmentA = new FragmentA();
                        ViewItem viewItem = new ViewItem();

                        //getting wheather info
                        String loc = wheatherTurkey.getLocation().getName();
                        String temp = String.valueOf(wheatherTurkey.getCurrent().getTempc()) + " C";
                        String status = wheatherTurkey.getCurrent().getCondition().getText();
                        String wind = String.valueOf(wheatherTurkey.getCurrent().getWind_mph());
                        String image = "https:" + wheatherTurkey.getCurrent().getCondition().getIcon();
                        String visibility = String.valueOf(wheatherTurkey.getCurrent().getVis_km());
                        String pressure = String.valueOf(wheatherTurkey.getCurrent().getPressure_mb());
                        String humidity = String.valueOf(wheatherTurkey.getCurrent().getHumidity());
                        fragmentA.getValues(loc,status,temp,wind,image,pressure,humidity,visibility);
                        viewItem.getValues(loc,temp,image);

                        //adding fragment to viewpager
                        itemList.add(viewItem);
                        slidePagerAdapter.notifyItemInserted(itemList.size()-1);
                        recyclerView.scrollToPosition(itemList.size()-1);
                        //slidePagerAdapter.setFragmentList(itemList);

                    }
                }
            }

            @Override
            public void onFailure(Call<WheatherTurkey> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    //get location if location permission is granted, request permission otherwise...
    public void getLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            retrieveLocation();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    //this method runs if only permission is granted, getting the latitude and longitude from gps...
    //then it retrieves the location with Geocoder
    @SuppressLint("MissingPermission")
    public void retrieveLocation(){
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);

        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location != null){
            double latitude, longitude;
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
                baseLocation = addressList.get(0).getAddressLine(0).split(",")[2].split("/")[1];
                setRetrofit(0);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //on permission request, if we allow the permission get the location, throw an error otherwise
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            retrieveLocation();
        }
        else{
            Toast.makeText(getApplicationContext(), "You should allow location permission!", Toast.LENGTH_LONG).show();

        }
    }
}