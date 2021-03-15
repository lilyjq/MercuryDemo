package com.mercury.demo.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.mercury.demo.util.DisplayUtil;

class AnimWaveView extends View {

    private Paint paint;
    private Path path;
    private int waveWdith = 800;
    public AnimWaveView(Context context) {
        super(context);
        init();
    }

    public AnimWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AnimWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        path = new Path();
        startAnimation();
    }



    private int dy;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        int orighinY = getHeight();
//        if(dy == 0){
//            dy = getHeight();
//        }
        int halfWidth = waveWdith/2;
        path.moveTo(-waveWdith+dx,dy);
        for(int i = -waveWdith;i<= getWidth()+waveWdith;i+= waveWdith){
            path.rQuadTo(halfWidth/2,-100,halfWidth,0);//相对第一个点的偏移
            path.rQuadTo(halfWidth/2,100,halfWidth,0);
        }
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        canvas.drawPath(path,paint);


    }

    int dx =0;

    public void startAnimation(){
        ValueAnimator animator = ValueAnimator.ofInt(0,waveWdith);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        animator.start();

        ValueAnimator animator1 = ValueAnimator.ofInt(get(),100);
        animator1.setDuration(18000);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dy = (int) animation.getAnimatedValue();
            }
        });

        animator1.start();

    }

/*    public int getScreenWidth(Context context){
        Point point=new Point();
        getDisplay(g).getRealSize(point);
        return point.x;
        //return point.y;
    }

    public  void getDispaly(Activity activity){

        activity.getWindowManager().getDefaultDisplay();
    }*/

    public  int  get(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return  display.getHeight();
    }


}
