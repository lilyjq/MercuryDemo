package com.mercury.demo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PathView extends androidx.appcompat.widget.AppCompatImageView {



    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private boolean isFrist = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = -1;
        if(pathMeasure != null ){
            if(isFrist){
             count = canvas.save();
            isFrist  = false;            }
            float end = mLength*mAnimationValue;
            pathMeasure.getSegment(0f,end,dst,true);
            canvas.drawPath(dst,paint);
        }
        if(isClear && count>-1){
            canvas.restoreToCount(count);
        }
        }


    Paint paint;

    List<Path> list ;
    private void init(){
        list = new ArrayList<>();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);

        Path path1 = new Path();
        path1.moveTo(100f,80f);
        path1.lineTo(100f,120f);

        Path path2 = new Path();
        path2.moveTo(100f,100f);
        path2.lineTo(300f,100f);

        Path path3 = new Path();
        path3.moveTo(300f,80f);
        path3.lineTo(300f,120f);

        Path path4 = new Path();
        path4.moveTo(320f,100f);
        path4.lineTo(360f,100f);

        Path path5 = new Path();
        path5.moveTo(340f,100f);
        path5.lineTo(340f,300f);

        Path path6 = new Path();
        path6.moveTo(320f,300f);
        path6.lineTo(360f,300f);

        list.add(path1);
        list.add(path2);
        list.add(path3);
        list.add(path4);
        list.add(path5);
        list.add(path6);
    }

    private void onePath(){
        Path path1 = new Path();
        path1.moveTo(100f,80f);
        path1.lineTo(100f,120f);

        path1.moveTo(100f,100f);
        path1.lineTo(300f,100f);

        path1.moveTo(300f,80f);
        path1.lineTo(300f,120f);

        path1.moveTo(320f,100f);
        path1.lineTo(360f,100f);

        path1.moveTo(340f,100f);
        path1.lineTo(340f,300f);

        path1.moveTo(320f,300f);
        path1.lineTo(360f,300f);
        PathMeasure pathMeasure = new PathMeasure(path1,false);
        Path path = new Path();
        pathMeasure.getSegment(0f,pathMeasure.getLength(),path,true);
        list.add(path);
        while (pathMeasure.nextContour()){
            Path path12 = new Path();
            pathMeasure.getSegment(0f,pathMeasure.getLength(),path12,true);
            list.add(path12);
        }
    }



    private boolean isClear = false;
    private Path dst = new Path();
    private PathMeasure pathMeasure;
    private float mLength;
    private float mAnimationValue;
    public  void startAnimation(final int x){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,1f);
        if(x%3 == 2){
            valueAnimator.setDuration(300);
        }else{
            valueAnimator.setDuration(300);
        }
        pathMeasure = new PathMeasure(list.get(x),false);
        mLength = pathMeasure.getLength();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimationValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                     dst.addPath(list.get(x));
                     if(x<list.size()-1){
                         startAnimation(x+1);
                     } else {
                         isClear = true;
                         pathMeasure = null;
                         postInvalidate();
                     }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

     valueAnimator.start();
    }




}
