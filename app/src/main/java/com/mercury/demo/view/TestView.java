package com.mercury.demo.view;

import android.content.Context;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;

public class TestView extends View {

    private int size = 5;
    private int x,y;
    private int angle;
    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int WidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int WidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int HeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int HeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

    }

    private void init(){
        angle = 360/size;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        int width = getWidth()-paddingStart-paddingEnd;
        int height  = getHeight()-paddingTop-paddingBottom;
        int r = Math.min(width,height)/2;
        x = width/2;
        y = height/2;

        for(int i = 0;i<size;i++){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(getRandomColor(i));
            canvas.drawArc(x-r,y-r,x+r,y+r,i*angle,angle,true,paint);
    }
    }

    private int getRandomColor(int i){
        switch (i){
            case 0:
                return Color.YELLOW;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.CYAN;
            case 4:
                return Color.RED;
            default:
                return Color.MAGENTA;
        }
    }
}
