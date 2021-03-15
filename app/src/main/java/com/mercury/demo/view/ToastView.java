package com.mercury.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.mercury.demo.R;

/**
 * 给toast加动画，自定义toast有个方法 setview 尝试将该view 变成带动画的自定义view
 */
public class ToastView extends FrameLayout {

//    @BindView(R.id.layout_content)
    View mContent;
    public ToastView(Context context) {
        super(context);
        init();
    }

    public ToastView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToastView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ToastView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
         LayoutInflater.from(getContext()).inflate(R.layout.layout_custon,this);
         mContent = findViewById(R.id.tv);
    }

    public void startAnimation(){
        TranslateAnimation animation = new TranslateAnimation(0,0,0,-800);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
//        alphaAnimation.setDuration(6000);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animation);
        set.addAnimation(alphaAnimation);
        set.setDuration(1000);
        set.setFillAfter(true);
//        mContent.startAnimation(set);
        mContent.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                mContent.setAlpha(1);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
