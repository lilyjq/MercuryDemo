package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mercury.demo.R;

class ScratchCardView extends View {

    private String text  = getResources().getString(R.string.price0)   ;
    private Paint mBitPaint,textPaint;
    private Bitmap BmpDST,BmpSRC,BmpText;
    private Path mPath;

   //SRC_OUT 橡皮擦 当目标图像有图像时结果显示空白像素，如果目标图像没有像素时显示源图像

    public ScratchCardView(Context context) {
        super(context);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        BmpSRC = BitmapFactory.decodeResource(getResources(),R.drawable.cat_1);
        BmpDST = Bitmap.createBitmap(BmpSRC.getWidth(),BmpSRC.getHeight(), Bitmap.Config.ARGB_8888);

        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.YELLOW);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(12);
        textPaint.setTextSize(18);
        textPaint.setTextAlign(Paint.Align.CENTER);




    }

    private float x0,y0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int acticon = event.getAction();
        switch (acticon){
            case MotionEvent.ACTION_DOWN:
                x0 = event.getX();
                y0 = event.getY();
                mPath.lineTo(x0,y0);
                    invalidate();


                return true;
            case MotionEvent.ACTION_MOVE:
                float endx = (x0+event.getX())/2;
                float endy = (y0+event.getY())/2;
                mPath.quadTo(x0,y0,endx,endy);//控制点 ，终点
                x0= event.getX();
                y0 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,getWidth()/2,getHeight()/2,textPaint);

        int layoutId =canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
        Canvas canvas1 = new Canvas(BmpDST);
        canvas1.drawPath(mPath,mBitPaint);

        canvas.drawBitmap(BmpDST,0,0,mBitPaint);

        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawBitmap(BmpSRC,0,0,mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layoutId);


    }
}
