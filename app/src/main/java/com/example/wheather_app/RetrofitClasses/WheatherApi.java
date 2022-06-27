package com.example.wheather_app.RetrofitClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WheatherApi {
    @GET("current.json?key=479985303ef54f46b3b141442221206&units=metric&aqi=no")
    Call<WheatherTurkey> getWheather(@Query("q") String cityName);
}
