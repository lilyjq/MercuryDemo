package com.mercury.demo.rxjava;

import com.google.gson.annotations.SerializedName;

public class WeatherResult {
    @SerializedName("detail")
    String detail;
    @SerializedName("weather")
    String weather;
    @SerializedName("time")
    String time;
}
