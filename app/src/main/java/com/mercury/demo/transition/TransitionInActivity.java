package com.mercury.demo.transition;

import android.graphics.Rect;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercury.demo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.ChangeBounds;
import androidx.transition.ChangeClipBounds;
import androidx.transition.ChangeScroll;
import androidx.transition.ChangeTransform;
import androidx.transition.Explode;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

public class TransitionInActivity extends AppCompatActivity implements View.OnClickListener {


    ConstraintLayout root;
    TextView tv,tv1,tv2,tv3,tv_1,tv_2,tv_3,tv4,tv5,tv6,tv7,tv8;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_in);
        setAnimation();
        root = findViewById(R.id.root);
        tv = findViewById(R.id.tv);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        setClick(tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8);

    }


    private void setClick(View...views){
        for(View view:views){
            view.setOnClickListener(this);
        }
//        tv1.setOnClickListener(this);
//        tv2.setOnClickListener(this);
//        tv3.setOnClickListener(this);
//        tv4.setOnClickListener(this);
//        tv5.setOnClickListener(this);
    }




    private void changeViewsVisible(View...view){
        for(View view1:view) {
            if (view1.getVisibility() == View.VISIBLE) {
                view1.setVisibility(View.INVISIBLE);
            } else {
                view1.setVisibility(View.VISIBLE);
            }
        }
    }


    private void setAnimation(){
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setEnterTransition(slide);

        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setExitTransition(explode);
    }
//slide explode fade extends Visibility 通过setVisible 去改变状态


    private int halfwidth = 0;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv1:
                Slide slide = new Slide();
                slide.setInterpolator(new AccelerateDecelerateInterpolator());
                TransitionManager.beginDelayedTransition(root,slide);
                changeViewsVisible(tv);
                break;
            case R.id.tv2:
                Explode explode = new Explode();
                explode.setInterpolator(new DecelerateInterpolator());
                TransitionManager.beginDelayedTransition(root,explode);
                changeViewsVisible(tv,tv_1,tv_2,tv_3);
                break;
            case R.id.tv3:
                Fade fade = new Fade();
                fade.setDuration(3000);
                fade.setInterpolator(new BounceInterpolator());
                TransitionManager.beginDelayedTransition(root,fade);
                changeViewsVisible(tv,tv_1,tv_2,tv_3);
                break;
            case R.id.tv4:
                Fade fade1 = new Fade();
                fade1.setInterpolator(new BounceInterpolator());
                Explode explode1 = new Explode();
                explode1.setInterpolator(new DecelerateInterpolator());
                TransitionSet set = new TransitionSet();
                set.addTransition(fade1).addTransition(explode1).setOrdering(TransitionSet.ORDERING_TOGETHER);
                TransitionManager.beginDelayedTransition(root,set);
                changeViewsVisible(tv,tv_1,tv_2,tv_3);
                break;
            case R.id.tv5:
                ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setInterpolator(new BounceInterpolator());
                TransitionManager.beginDelayedTransition(root,changeBounds);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tv_2.getLayoutParams();
                if(halfwidth == 0 ){
                    halfwidth= (int) (tv_2.getWidth()/2.0f);
                }
                if(tv_2.getWidth() == halfwidth){
                    params.width = halfwidth*2;
                }else{
                    params.width = halfwidth;
                }
                tv_2.setLayoutParams(params);
                break;
            case R.id.tv6:
                //当调用 view.setClipBounds() 时会触发转场效果：
                ChangeClipBounds changeClipBounds = new ChangeClipBounds();
                changeClipBounds.setInterpolator(new BounceInterpolator());
                TransitionManager.beginDelayedTransition(root,changeClipBounds);
                int width = tv_3.getWidth();
                int height = tv_3.getHeight();
                int gap = 20;
                Rect rect = new Rect(0, gap, width, height - gap);
                if (rect.equals(tv_3.getClipBounds())) {
                    tv_3.setClipBounds(null);
                } else {
                    tv_3.setClipBounds(rect);
                }
                break;
            case R.id.tv7:
                //当调用 view.scrollTo() 会触发转场效果：
                ChangeScroll changeScroll = new ChangeScroll();
                changeScroll.setInterpolator(new AnticipateOvershootInterpolator());
                TransitionManager.beginDelayedTransition(root, changeScroll);
                if (tv_1.getScrollX() == -100 && tv_1.getScrollY() == -100) {
                    tv_1.scrollTo(0, 0);
                } else {
                    tv_1.scrollTo(-100, -100);
                }
                break;
            case R.id.tv8:
//                当调用 view.scrollTo() 会触发转场效果：
                ChangeTransform transition = new ChangeTransform();
                transition.setInterpolator(new OvershootInterpolator());
                TransitionManager.beginDelayedTransition(root, transition);
                if (tv.getTranslationX() == 100 && tv.getTranslationY() == 100) {
                    tv.setTranslationX(0);
                    tv.setTranslationY(0);
                } else {
                    tv.setTranslationX(100);
                    tv.setTranslationY(100);
                }
                if (tv_1.getRotationX() == 30f) {
                    tv_1.setRotationX(0);
                } else {
                    tv_1.setRotationX(30);
                }
                if (tv_2.getRotationY() == 30f) {
                    tv_2.setRotationY(0);
                } else {
                    tv_2.setRotationY(30);
                }
                if (tv_3.getScaleX() == 0.5f && tv_3.getScaleY() == 0.5f) {
                    tv_3.setScaleX(1f);
                    tv_3.setScaleY(1f);
                } else {
                    tv_3.setScaleX(0.5f);
                    tv_3.setScaleY(0.5f);
                }
                break;
            default:
                break;



        }
    }
}
