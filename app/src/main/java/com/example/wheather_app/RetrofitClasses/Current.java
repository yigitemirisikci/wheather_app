package com.example.wheather_app.RetrofitClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("condition")
    @Expose
    private Condition condition;

    @SerializedName("temp_c")
    @Expose
    private double tempc;

    @SerializedName("pressure_mb")
    @Expose
    private double pressure_mb;

    @SerializedName("humidity")
    @Expose
    private double humidity;

    @SerializedName("vis_km")
    @Expose
    private double vis_km;

    @SerializedName("wind_mph")
    @Expose
    private double wind_mph;


    public Condition getCondition() {
        return condition;
    }

    public double getTempc() {
        return tempc;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWind_mph() {
        return wind_mph;
    }

    public double getVis_km() {
        return vis_km;
    }
}
