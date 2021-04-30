package com.mercury.demo;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.mercury.MainAdapter;
import com.mercury.demo.aty.NoticeActivity;
import com.mercury.demo.aty.PathActivity;
import com.mercury.demo.aty.ProgressActivity;
import com.mercury.demo.aty.RadarViewActivity;
import com.mercury.demo.aty.SnackbarTestActivity;
import com.mercury.demo.aty.TestLayoutActivity;
import com.mercury.demo.video.VideoActivity;
import com.mercury.demo.aty.WaterMaskActivity;
import com.mercury.demo.behavior.CoordinateActivity;
import com.mercury.demo.camera.AvatarActivity;
import com.mercury.demo.motionlayout.YouTubeActivity;
import com.mercury.demo.scrollcut.ScrollCutAcitivty;
import com.mercury.demo.transition.TransitionActivity;
import com.mercury.demo.util.HomeWatcherReceiver;
import com.mercury.demo.viewpager.TabHostActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class MainActivity extends AppCompatActivity implements MainAdapter.onItemClickListenr {


   RecyclerView recyclerView;
   MainAdapter adapter;
   HomeWatcherReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this);
        adapter.setListenr(this);
        recyclerView.setAdapter(adapter);

        receiver = new HomeWatcherReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//        registerReceiver(receiver,filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        try {
//            unregisterReceiver(receiver);
//        }catch (Exception e){
//
//        }
    }

    @Override
    public void onItemClick(int pos) {
        switch (pos) {
            case 0:
                startActivity(new Intent(this, AvatarActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, TestLayoutActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, RadarViewActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, TabHostActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, CoordinateActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, PathActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, ScrollCutAcitivty.class));
                break;
            case 8:
                startActivity(new Intent(this, NoticeActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, YouTubeActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, TransitionActivity.class));
                break;
            case 11:
                startActivity(new Intent(this, WaterMaskActivity.class));
                break;
            case 12:
                startActivity(new Intent(this, SnackbarTestActivity.class));
                break;
            case 13:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case 14:
                break;
            default:
                break;
        }
        Handler handler = new Handler(getMainLooper());

    }

    /**
     * 判断是Navigationbar 是否显示 有的手机可以缩回
     * mBottomView 页面最底部的view
     */
/*    private void setBottomHeight(){
        mBotoomView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                mBotoomView.getGlobalVisibleRect(rect);
                DisplayMetrics outMetrics = new DisplayMetrics();
                WindowManager windowManager = getWindowManager();
                windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
                boolean navigationBarShow = rect.bottom < outMetrics.heightPixels;
                SubToastUtil.setIsNavigationShow(navigationBarShow);
            }
        });

    }*/

    @SuppressLint("SupportAnnotationUsage")
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.BATTERY_STATS})
    private void requestPermission(){

    }
    public static final int TYPE_ONE = 10;
    public static final int TYPE_K = 101;
    public static final int TYPE_O = 88;

    /**
     * https://www.cnblogs.com/whoislcj/p/5677917.html
     */
    @IntDef({TYPE_ONE, TYPE_K, TYPE_O})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Animation{

    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_ONE, TYPE_K, TYPE_O})
    public @interface ResizeMode {
    }


    @SuppressLint("UniqueConstants")
    @Retention(RetentionPolicy.SOURCE)
    @IntDef()
    public @interface MY {
        int TYPE_1 = 1;
        int TYPE_2 = 1;
        int TYPE_3 = 1;
    }

    @FindViewById(R.id.recycler)
    ImageView iv;
    @setOnCLickListenter(id = R.id.recycler,methodName = "click")
    ImageView imageView;

    void click(){
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FindViewById{
        int value();
    }


    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface setOnCLickListenter{
        int id();
        String methodName();
    }
}
