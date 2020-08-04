package com.mercury.demo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.mercury.demo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class StarView extends View {


    public StarView(Context context) {
        super(context);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Resources resources;

    private Bitmap bgBitmap;
    private Bitmap starOneBitmap;
    private Bitmap starTwoBitmap;
    private Bitmap starThreeBitmap;

    private void init(Context context){
        resources = context.getResources();
        bgBitmap = ((BitmapDrawable)resources.getDrawable(R.drawable.xk)).getBitmap();
        starOneBitmap = ((BitmapDrawable)resources.getDrawable(R.drawable.star1)).getBitmap();
        starTwoBitmap = ((BitmapDrawable)resources.getDrawable(R.drawable.star2)).getBitmap();
        starThreeBitmap = ((BitmapDrawable)resources.getDrawable(R.drawable.star3)).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    List<StarBean> list;

    private  void initData(){
        list = new ArrayList<>();
    }

    private static final float [][] START_LOCATION = new float[][]{
            {0.5f,0.2f},{0.68f,0.35f},{0.5f,0.05f},{0.15f,0.15f},{0.5f,0.5f},{0.15f,0.8f},{0.2f,0.3f},
            {0.77f,0.4f},{0.75f,0.5f},{0.8f,0.55f},{0.9f,0.6f},{0.1f,0.7f},{0.1f,0.1f},{0.7f,0.8f},{0.5f,0.6f}
    };


 /*   private int getDirection(float[][] location){




    }*/

    class POINT{

    }

}
