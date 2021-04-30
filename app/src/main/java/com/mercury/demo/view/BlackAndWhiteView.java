package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mercury.demo.R;

import java.util.logging.Logger;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by ljq
 * on 2021-03-14.
 * <p> </p>
 */
public class BlackAndWhiteView extends AppCompatImageView {

    public BlackAndWhiteView(Context context) {
        super(context);
        init();
    }

    public BlackAndWhiteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlackAndWhiteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private Paint mPaint;
//    private Bitmap bitmap;
    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0,0,0,1,0
        });
//        matrix.setRotate();
//        matrix.setSaturation(); 设置饱和度
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.u);
    }


    @Override
    public void setImageBitmap(Bitmap bm) {
//        super.setImageBitmap(bm);
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
//        qq清空画布

//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//        super.onDraw(canvas);

//        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
//        canvas.drawBitmap(bitmap,null,new RectF(0,0,getWidth(),getHeight()),mPaint);
    }
}
