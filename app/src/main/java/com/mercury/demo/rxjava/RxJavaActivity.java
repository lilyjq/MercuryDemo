package com.mercury.demo.rxjava;

import android.app.Activity;
import android.os.Bundle;


import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void test(){
        Observable<LoveReulst> observable1 = ApiHelp.getTestService().getLove(new LoveRequestBody());
        Observable<WeatherResult> observable2 = ApiHelp.getTestService().getWeather(new WeatherRequestBody());

//        Observable<SimpleResult> observable = Observable.merge(observable1,observable2).subscribe()





        //1.simple
        Disposable disposable = ApiHelp.getTestService().getLove(new LoveRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoveReulst>() {
                    @Override
                    public void accept(LoveReulst loveReulst) {
                        //do result
                    }
                });

        //2.flatmap
        Disposable disposable2 = ApiHelp.getTestService().getLove(new LoveRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<LoveReulst, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final LoveReulst loveReulst) throws Exception {
                        if(loveReulst != null){
                            return new Observable<String>() {
                                @Override
                                protected void subscribeActual(Observer<? super String> observer) {
                                      observer.onNext(loveReulst.toString());
                                }
                            };
                        }
                        return null;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s)   {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable)  {

                    }
                });

    /*    Disposable disposable21 = ApiHelp.getTestService().getLoveList(new LoveRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<LoveReulst>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(List<LoveReulst> loveReulsts) throws Exception {
                        return Observable.just(loveReulsts.);
                    }
                })*/

        //3.map
        Disposable disposable3 = ApiHelp.getTestService().getLove(new LoveRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<LoveReulst, String>() {
                    @Override
                    public String apply(LoveReulst loveReulst) throws Exception {
                        return "";
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });

        Disposable disposable4 = ApiHelp.getTestService().getLove(new LoveRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .compose()
                .map(new Function<LoveReulst, String>() {
                    @Override
                    public String apply(LoveReulst loveReulst) throws Exception {
                        return null;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });


    }
}
