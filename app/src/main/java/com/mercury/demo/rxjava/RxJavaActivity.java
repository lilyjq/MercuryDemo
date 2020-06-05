package com.mercury.demo.rxjava;

import android.app.Activity;
import android.os.Bundle;


import androidx.annotation.Nullable;
import io.reactivex.Observable;

public class RxJavaActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void test(){
        Observable<LoveReulst> observable1 = ApiHelp.getTestService().getLove(new LoveRequestBody());
        Observable<WeatherResult> observable2 = ApiHelp.getTestService().getWeather(new WeatherRequestBody());
    }
}
