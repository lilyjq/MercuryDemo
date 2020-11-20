package com.mercury.demo.rxjava;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestService {

    @POST("api/v1/getWeather")
    Observable<WeatherResult> getWeather(@Body WeatherRequestBody requestBody);

    @POST("api/v1/getLove")
    Observable<LoveReulst> getLove(@Body LoveRequestBody requestBody);

    @POST("api/v1/getLove")
    Observable<List<LoveReulst>> getLoveList(@Body LoveRequestBody requestBody);
}
