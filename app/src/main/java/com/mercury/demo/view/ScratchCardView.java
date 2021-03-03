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
import com.mercury.demo.util.DisplayUtil;

/**
 * 刮刮卡
 */
class ScratchCardView extends View {

    private String text  = getResources().getString(R.string.price0)   ;
    private Paint mBitPaint,textPaint;
    private Bitmap srcBmp, dstBmp,BmpText;
    private Path mPath;

   //SRC_OUT 橡皮擦 当目标图像有图像时结果显示空白像素，如果目标图像没有像素时显示源图像

    public ScratchCardView(Context context) {
        super(context);
        init();
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        dstBmp = BitmapFactory.decodeResource(getResources(),R.drawable.dog);
        srcBmp = Bitmap.createBitmap(dstBmp.getWidth(), dstBmp.getHeight(), Bitmap.Config.ARGB_8888);

        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint .setColor(Color.RED);
        mBitPaint.setStyle(Paint.Style.STROKE);
        mBitPaint.setStrokeWidth(60);


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint.setColor(Color.GRAY);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(DisplayUtil.dp2px(28));
        textPaint.setTextAlign(Paint.Align.LEFT);
        mPath = new Path();




    }

    private float x0,y0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int acticon = event.getAction();
        switch (acticon){
            case MotionEvent.ACTION_DOWN:
                x0 = event.getX();
                y0 = event.getY();
                mPath.moveTo(x0,y0);
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
        canvas.drawText(text,50,400,textPaint);

        int layoutId =canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);

            Canvas canvas1 = new Canvas(srcBmp);
            canvas1.drawPath(mPath, mBitPaint);


        canvas.drawBitmap(dstBmp,0,0,mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        //
        //DST_OUT  相交区域显示dst图像，目标图像的透明度和原图像相反，当原图形的透明度是100%相交区域为空 为0 完全显示目标图像
        //Xfermode设置之前的图像叫目标图像（给谁应用xmode）之后为源图像(拿什么应用xmode)
        canvas.drawBitmap(srcBmp,0,0,mBitPaint);
        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layoutId);


    }
}
