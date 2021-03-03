package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.mercury.demo.R;

class BlurMaskFilterView extends View {
    public BlurMaskFilterView(Context context) {
        super(context);
        init();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }



    private Bitmap bitmapSrc;

    Bitmap alphaBitmap;
    Paint paint;
    int[] offsetXy = new int[2];
    private void init(){
        bitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.cat_1);
        Paint  mPaint = new Paint();
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(6, BlurMaskFilter.Blur.NORMAL);
        mPaint.setMaskFilter(blurMaskFilter);
        alphaBitmap = bitmapSrc.extractAlpha(mPaint,offsetXy);
        paint= new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制阴影
        canvas.drawBitmap(alphaBitmap,0,0,paint);
        canvas.drawBitmap(bitmapSrc,-offsetXy[0],-offsetXy[1],null);


    }
}
