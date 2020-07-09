package com.mercury.demo.scrollcut;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mercury.demo.util.DisplayUtil;

public class ScrollCutView extends androidx.appcompat.widget.AppCompatImageView {


    private Rect rect;
    private int mWidth = (int) DisplayUtil.dp2px(100);
    private Bitmap bitmap;
    private Matrix matrix;
    private Paint paint;


    public ScrollCutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        rect = new Rect(mWidth/2,0,mWidth,mWidth);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
//        super.setImageBitmap(bm);
        bitmap = bm;

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        resetRect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmap != null){
           canvas.drawBitmap(bitmap,rect,rect,paint);
        }
    }

    private void resetRect() {
        if(bitmap == null)
            return;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        matrix = new Matrix();
        int top = 0, bottom = mWidth, left = 0, right = mWidth;
        if (height > mWidth && width > mWidth) {
            top = (int) ((height - mWidth) / 2.0f);
            bottom = top + mWidth;
            left = (int) ((width - mWidth) / 2.0f);
            right = left + mWidth;
        } else {
            float scaleX = mWidth * 1.0f / width;  //2 数值越大 说明差的越多 需要用它
            float scaleY = mWidth * 1.0f / height; //1.5
            if (height < mWidth && width < mWidth) {
                if (scaleY >= scaleX) {//如果
                    matrix.postScale(scaleY, scaleY);
                } else {
                    matrix.postScale(scaleX, scaleX);
                }
            } else if (height > mWidth) {
                matrix.postScale(scaleX, scaleX);
            } else if (width > mWidth) {
                matrix.postScale(scaleY, scaleY);
            }
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            height = bitmap.getHeight();
            width = bitmap.getWidth();
            if (height > mWidth) {
                top = (int) ((height - mWidth) / 2.0f);
                bottom = top + mWidth;
            }
            if (width > mWidth) {
                left = (int) ((width - mWidth) / 2.0f);
                right = left + mWidth;
            }
        }
        bitmap = Bitmap.createBitmap(bitmap, left,top,mWidth,mWidth);
        rect.set(mWidth/2, 0, mWidth, mWidth);
    }

    public void changeRect(int left){
        rect.set(left,getTop(),getRight(),getBottom());
        postInvalidate();
    }

}
