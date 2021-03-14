package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by ljq
 * on 2020-03-26.
 * <p> </p>
 */
public class BasisView extends View {
    public BasisView(Context context) {
        super(context);
    }

    public BasisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
       /* paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        canvas.drawCircle(190,200,150,paint);*/
     /*  paint.setColor(0xFFFF0000);
       paint.setStyle(Paint.Style.FILL);
       paint.setStrokeWidth(50);
       canvas.drawCircle(190,200,150,paint);

       paint.setColor(0x7EFFFF00);
        canvas.drawCircle(190,200,100,paint);*/


     pathTest(canvas);

    }


    private void pathTest(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        Path path = new Path();
        path.moveTo(10,10);
        RectF rectF = new RectF(100,10,600,400);
        path.arcTo(rectF,0,359,true);
        canvas.drawPath(path,paint);

        Paint paint1 = new Paint();
        paint1.setColor(Color.GRAY);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(12);

        canvas.drawRect(rectF,paint1);

    }


    private void RegionTest(Canvas canvas){
        Paint paint  = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);

        Path ovalPath = new Path();
        RectF rectF = new RectF(50,50,200,500);
        ovalPath.addOval(rectF,Path.Direction.CCW);
        Region region = new Region();
        region.setPath(ovalPath,new Region(50,50,200,200));
        drawRegin(canvas,region,paint);


    }


    private void drawRegin(Canvas canvas,Region region,Paint paint){
        RegionIterator iterator = new RegionIterator(region);
        Rect r = new Rect();
        while (iterator.next(r)){
            canvas.drawRect(r,paint);
        }
    }
}
