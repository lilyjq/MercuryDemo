package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.mercury.demo.R;
import com.mercury.demo.data.RaBean;

import java.util.List;

import androidx.annotation.Nullable;

public class RadarView extends View {

    Paint paint;
    Paint pointPaint;
    Paint coverPaint;
    int size = 5;
    int lineSize = 5;

    CornerPathEffect effect;
    int max = 10;



    public RadarView(Context context) {
        super(context);
        initView(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.color_pink_line));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);



        coverPaint = new Paint();
        coverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        coverPaint.setColor(context.getResources().getColor(R.color.color_pink));
        coverPaint.setAntiAlias(true);
        coverPaint.setStrokeWidth(5);

        pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(context.getResources().getColor(R.color.color_yellow));
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(20);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        effect = new CornerPathEffect(30.f);
        coverPaint.setPathEffect(effect);
        pointPaint.setPathEffect(effect);
    }
    int centerX ;
    int centerY ;
    int raduis;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        int width = getWidth()-paddingStart-paddingEnd;
        int height  = getHeight()-paddingTop-paddingBottom;
         centerX = width/2;
         centerY = height/2;
         raduis = width>height? height/2-10 :width/2-10;

//        canvas.drawPoint(100,100,pointPaint);


        for(int i = 1;i<lineSize+1;i++){
            int radui = raduis/lineSize*i;
            drawMp(canvas,radui);
        }
        if(list != null &&list.size()>4) {
            Path path = new Path();
            for (int i = 0; i < 5; i++) {
                double angle = Math.PI / 180 * (360 / size * i - 90);
                double a = 1.0;
                double precent = list.get(i).point*a / max*a;
                float x = (float) (centerX + raduis * Math.cos(angle) * precent);
                float y = (float) (centerY + raduis * Math.sin(angle) * precent);
                canvas.drawPoint(x, y, coverPaint);
                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, coverPaint);
        }




    }

    List<RaBean> list;
    public void setList(List<RaBean> list){
        this.list = list;
        postInvalidate();
    }


    private void drawMp(Canvas canvas,int raduis){
        Path path = new Path();
        paint.setPathEffect(effect);
        for(int i= 0;i<size;i++){
            double angle = Math.PI/180*(360/size*i-90);
            float x = (float) (centerX+raduis*Math.cos(angle));
            float y = (float) (centerY+raduis*Math.sin(angle));
//            canvas.drawPoint(x,y,pointPaint);
            if(i == 0) {
                path.moveTo(x, y);
            }else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path,paint);
    }
}
