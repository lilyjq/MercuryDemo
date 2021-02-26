package com.mercury.demo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.ResourceDecoder;
import com.mercury.demo.R;

class TelescopeView  extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mBitmapBg;

    public TelescopeView(Context context) {
        super(context);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xk);
    }

    private float mDx,mDy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int acticon = event.getAction();
        switch (acticon){
            case MotionEvent.ACTION_DOWN:
                mDx = event.getX();
                mDy = event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = event.getX();
                mDy = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mDx = -1;
                    mDy = -1;
                break;

        }
        invalidate();
        return super.onTouchEvent(event);
    }

    private BitmapShader shader;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mBitmapBg == null){
            mBitmapBg = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas1 = new Canvas(mBitmapBg);//将bitmap 拉伸和屏幕一样大小
            canvas1.drawBitmap(mBitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);
            shader =  new BitmapShader(mBitmapBg, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        }
        if(mDx != -1 && mDy != -1 && shader!= null){
            mPaint.setShader(shader);
            canvas.drawCircle(mDx,mDy,150,mPaint);
        }



    }
}
