package com.mercury.demo.rxjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelp {

    private static class SingletonHolder{
        private static final RetrofitHelp INSTANCE = new RetrofitHelp();
    }

    private  RetrofitHelp() {
    }


    private OkHttpClient okHttpClient( ){
        File cacheFile = new File("");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60*1000, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(new Cache(cacheFile, 1024))
                .build();
        return client;
    }
    public static RetrofitHelp getHelper() {
        return SingletonHolder.INSTANCE;
    }
    String baseurl = "https://www.baidu.com/";
    public Retrofit retrofit() {
        String url = baseurl;
        return retrofit(url);
    }


    public Retrofit retrofit(String url) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
