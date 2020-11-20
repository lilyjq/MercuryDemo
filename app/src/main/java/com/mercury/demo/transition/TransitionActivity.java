package com.mercury.demo.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.mercury.demo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TransitionActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        setAnimation();
        imageView = findViewById(R.id.iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransitionActivity.this,TransitionInActivity.class), ActivityOptions.makeSceneTransitionAnimation(TransitionActivity.this).toBundle());
//                TransitionActivity.this.overridePendingTransition(R.anim.flip_in,R.anim.flip_out);
            }
        });
    }

//    Window.setEnterTransition() 设置进场动画
//    Window.setExitTransition() 设置出场动画
//    Window().setReturnTransition() 设置返回activity时动画
//    Window().setReenterTransition() 设置重新进入时动画





    private void setAnimation(){
// slide 滑动、 explode分解  fade 淡入淡出
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        //退出时使用
        getWindow().setExitTransition(explode);
        //第一次进入时使用
        getWindow().setEnterTransition(explode);
        //再次进入时使用
//        getWindow().setReenterTransition(explode);

//
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setReturnTransition(slide);
    }
}
