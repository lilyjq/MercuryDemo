package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
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
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60);
        textPaint.setColor(getContext().getResources().getColor(R.color.textcolor));


        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));

    }

    int baseX,baseY;

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊ";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        baseX = (int) ((getWidth()-textPaint.measureText(TEXT))/2);
        baseY = (int) (getHeight()/2 - (textPaint.descent()+textPaint.ascent())/2);
        canvas.drawText(TEXT,baseX,baseY,textPaint);
        canvas.drawLine(baseX,baseY,baseX+330,baseY,linePaint);

        linePaint.setColor(getContext().getResources().getColor(R.color.color_pink));
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,linePaint);
    }
}
