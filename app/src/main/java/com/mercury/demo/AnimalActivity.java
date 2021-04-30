package com.mercury.demo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by ljq
 * on 2020-05-05.
 * <p> </p>
 */
public class AnimalActivity extends Activity {


    TextView  tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        tv =findViewById(R.id.tv);
        testPropertyValuesHolder();
    }



    private void testPropertyValuesHolder(){
        PropertyValuesHolder rotationValuesHolder = PropertyValuesHolder.ofFloat("Rotation",60f,-60f,40f,-40f,-20f,20f,10f,-10f,0);
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha",0.1f,1f,0.1f,1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv,rotationValuesHolder,alphaHolder);
        animator.setDuration(2000);
        animator.start();
    }
}
