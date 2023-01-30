package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mercury.demo.R;

/**
 * 圆滑的线、贝塞尔
 */
class MyTouchView extends View {

    private Paint paint;
    private Path path;
    private Path path2;

    public MyTouchView(Context context) {
        super(context);
        init();
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.textcolor));
        paint.setStrokeWidth(15);

        path = new Path();
        path2 = new Path();
    }



    private float x0,y0;
    private float x1,y1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int acticon = event.getAction();
        switch (acticon){
            case MotionEvent.ACTION_DOWN:
                x0 = event.getX();
                y0 = event.getY();
                if(i == 0) {
                    path2.moveTo(x0,y0);
                    path.moveTo(x0, y0);//起始点
                    x1 = event.getX();
                    y1 = event.getY();
                    i++;
                }else{
                    path.lineTo(x0,y0);
                    i++;
                    float endx = (x1+event.getX())/2;
                    float endy = (y1+event.getY())/2;
                    path2.quadTo(x1,y1,endx,endy);//控制点 ，终点
                    x1= event.getX();
                    y1 = event.getY();
                    invalidate();
                }

                 return true;
            case MotionEvent.ACTION_MOVE:


                 break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onTouchEvent(event);
    }


    private int i = 0;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.color_pink));
        canvas.drawPath(path,paint);

        paint.setColor(getResources().getColor(R.color.textcolor));

        canvas.drawPath(path2,paint);

        paint.setColor(Color.RED);
        canvas.drawPoint(x0,y0,paint);
        canvas.drawPoint(x1,y1,paint);
    }

    @Override public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
