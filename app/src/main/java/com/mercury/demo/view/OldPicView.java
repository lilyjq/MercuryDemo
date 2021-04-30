package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by ljq
 * on 2021-03-14.
 * <p> </p>
 */
public class OldPicView extends AppCompatImageView {
    public OldPicView(Context context) {
        super(context);
        init();
    }

    public OldPicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OldPicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private Paint mPaint;
    //    private Bitmap bitmap;
    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.u);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//第一种方式
//        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if(drawable != null){
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            Log.d("bitmap","bitmap!= NULL");
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawBitmap(bitmap,null,new RectF(0,0,getWidth(),getHeight()),mPaint);
        }else{
            super.onDraw(canvas);
        }

//        super.onDraw(canvas);

//        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
//        canvas.drawBitmap(bitmap,null,new RectF(0,0,getWidth(),getHeight()),mPaint);
    }
}
