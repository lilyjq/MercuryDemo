package com.mercury.demo.motionlayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.mercury.MainAdapter;
import com.mercury.demo.R;
import com.mercury.demo.viewpager.ListItemAdapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MotionTestActivity extends Activity {

//https://blog.csdn.net/zzx752904457/article/details/121922421
    //https://juejin.cn/post/6943530210222571528
    //https://blog.csdn.net/baidu_33331545/article/details/126389524  MotionLayout 与 NestedScrollView 滑动的问题
    //https://www.jianshu.com/p/750a4c9e3ae0 MotionLayout 配合 RecyclerView 使用
    //https://blog.csdn.net/chuyouyinghe/article/details/130947178 钟带你学会MotionLayout 第二篇

    View top;
    View bottom;
    int topH;
    int boH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinate6);


        //
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ListItemAdapter adapter = new ListItemAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //AppBarLayout appBar = findViewById(R.id.appbar);

         top = findViewById(R.id.tv_top);
         bottom = findViewById(R.id.tv_bottom);

        //MotionLayout motionLayout = findViewById(R.id.montion);

        //appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
        //    @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //        if(appBar.getTotalScrollRange()>0) {
        //          float b = (verticalOffset / appBar.getTotalScrollRange());
        //
        //
        //        }
        //    }
        //});



        //MotionLayout motionLayout = findViewById(R.id.layout);
        //recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //    @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        //        super.onScrolled(recyclerView, dx, dy);
        //        if(dy>0){
        //            if( motionLayout.getCurrentState()== motionLayout.getStartState()){
        //                motionLayout.transitionToEnd();
        //            }
        //        }else{
        //            if(motionLayout.getCurrentState()== motionLayout.getEndState()) {
        //                motionLayout.transitionToStart();
        //            }
        //        }
        //    }
        //});


    }



    // a - b
    // a oncreate onstart onresume onpuase b oncreate onstart onresume a onstop
    //b finish b onpuase a onrestart onstart onresume b onstop ondestroy


    /*// activity                fragemnt

    oncreteview
    onattachfragment



    oncreate
                                onattach
                                oncreate
                                oncreateView
                                onViewCreate
                                onActiviyCreate
                                onstart



    onstart



                                onresume
    onresume


                                onpuase
    onpuase


                                onstop

    onstop
                                ondestroyview
                                ondestory
                                ondettach



    ondestory


*/}
