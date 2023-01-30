package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PathDashPathEffect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mercury.demo.R;

import androidx.annotation.Nullable;

public class MyTextView extends View {
    public MyTextView(Context context) {
        super(context);
        initPaint();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    Paint textPaint;
    Paint linePaint;
    private void initPaint(){
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60);
        textPaint.setColor(getContext().getResources().getColor(R.color.textcolor));
//        textPaint.setUnderlineText(true);


        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(10);
        linePaint.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        linePaint.setStrokeCap(Paint.Cap.ROUND);
//        linePaint.setPathEffect(new DashPathEffect())
//        linePaint.setPathEffect(new PathDashPathEffect())



    }

    int baseX,baseY;

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊ田";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        baseX = (int) ((getWidth()-textPaint.measureText(TEXT))/2);

        //textpaint.descent descent线到base的距离  textPaint.ascent() ascent线到base的距离
        baseY = (int) (getHeight()/2 - (textPaint.descent()+textPaint.ascent())/2);

        Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();
        int top = baseY+fm.top;
        int bottom = baseY +fm.bottom;
        int height = bottom-top;
        int wdith = (int) textPaint.measureText(TEXT);
//        int [] color = {Color.RED,Color.GREEN, Color.BLUE};
        int[] color= {R.color.textcolor,R.color.colorPrimary,R.color.colorAccent};
        float[] position = {0.3f,0.5f,0.7f};
        final LinearGradient linearGradient = new LinearGradient(0, 0,getMeasuredWidth(), 0,color,position, Shader.TileMode.CLAMP);
        textPaint.setShader(linearGradient);

        canvas.drawText(TEXT,baseX,baseY,textPaint);
        canvas.drawLine(baseX,baseY,baseX+wdith,baseY,linePaint);

        linePaint.setColor(getContext().getResources().getColor(R.color.color_pink));
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,linePaint);
    }
}
