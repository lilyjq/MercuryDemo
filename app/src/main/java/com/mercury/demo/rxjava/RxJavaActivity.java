package com.mercury.demo.rxjava;

import android.app.Activity;
import android.os.Bundle;

import io.reactivex.Notification;
import io.reactivex.functions.BooleanSupplier;
import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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

    /*    Observable<SimpleResult> observable = Observable.zip(observable1, observable2, new BiFunction<LoveReulst, WeatherResult, SimpleResult>() {
            @Override
            public SimpleResult apply(LoveReulst loveReulst, WeatherResult weatherResult) throws Exception {
                return null;
            }
        })*/

     /*   Observable<SimpleResult> observable = Observable.merge(observable1, observable2).subscribe(new Consumer<Object>() {
        })
        })*/



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
//                .compose(new AsyncTransformer<>())
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



//
//        Disposable disposable41 = ApiHelp.getTestService().getLove(new LoveRequestBody())
//                .compose(new AsyncTransformer<LoveReulst>())
//                .map(new Function<LoveReulst, String>() {
//                })


    Disposable disposable1 =  Observable.just("22","ee","maliao").repeat(3).observeOn(Schedulers.io())
          .subscribeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<String>() {
            @Override public void accept(String s) throws Exception {

            }
          });

    //repeatUnitl 如果返回 true则不repeat false则repeat.主要用于动态控制

      //Observable.just("22","ee","maliao").repeatUntil(new BooleanSupplier() {
      //  @Override public boolean getAsBoolean() throws Exception {
      //    return false;
      //  }
      //})


      //https://blog.csdn.net/jackzhang_0522/article/details/77337086

      //delay 延迟一段指定的时间再发射来自Observable的发射物
      //注意：delay 不会平移 onError 通知，它会立即将这个通知传递给订阅者，同时丢弃任何待 发射的 onNext 通知。然而它会平移一个 onCompleted 通知


// delaySubscription   让你可以延迟订阅原始Observable


      //doOnEach  注册一个回调，它产生的Observable每发射一项数据就会调用它一次
    Observable.range(0,3).doOnEach(new Consumer<Notification<Integer>>() {
      @Override public void accept(Notification<Integer> integerNotification) throws Exception {

      }
    }).subscribe(new Consumer<Integer>() {
      @Override public void accept(Integer integer) throws Exception {

      }
    });



      //doOnNext
      Observable.range(0,3).doOnNext(new Consumer<Integer>() {
        @Override public void accept(Integer integer) throws Exception {

        }
      }).subscribe(new Consumer<Integer>() {
        @Override public void accept(Integer integer) throws Exception {

        }
      });

//doOnSubscribe  doOnComplete   doOnError   doOnTerminate  doFinally  doOnDispose


      //timeInterval 一个发射数据的Observable转换为发射那些数据发射时间间隔的Observable  timeout
    }




}
