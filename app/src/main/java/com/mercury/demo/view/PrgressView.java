package com.mercury.demo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mercury.demo.R;
import com.mercury.demo.util.DisplayUtil;

import androidx.annotation.Nullable;

public class PrgressView extends View {
    public PrgressView(Context context) {
        super(context);
    }

    public PrgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PrgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    Rect rect;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;
        int raduis = centerX-ringWidth;
        int raduis1 = centerX-ringWidth;
        float sweepAngle = 360*mPercnet*aFloat;//angel
        canvas.drawCircle(centerX,centerY,centerX-ringWidth,ringPiant);
//        RectF rectF = new RectF(30,30,getWidth()-30,getHeight()-30);
//        canvas.drawArc(rectF,-90,100,false,coverPaint);

        textPaint.getTextBounds(title1,0,title1.length(),rect);
        canvas.drawText(title1, centerX - rect.width() / 2, centerY -centerY/4 + rect.height()/2, textPaint);


        textPaint2.getTextBounds(title2,0,title2.length(),rect);
        canvas.drawText(title2,centerX- rect.width()/2,centerY+rect.height()/2,textPaint2);


        textPaint.getTextBounds(title3,0,title3.length(),rect);
        canvas.drawText(title3,centerX-rect.width()/2,centerY+centerY/4+rect.height()/2,textPaint);



        canvas.drawArc(ringWidth,ringWidth,getWidth()-ringWidth,getHeight()-ringWidth,-90,sweepAngle,false,coverPaint);
        if(sweepAngle>0 && sweepAngle<360) {
            double angle = Math.PI/180*sweepAngle;
            float x = (float) (centerX+Math.sin(angle)*raduis);
            float y = (float) (centerY-Math.cos(angle)*raduis);
            canvas.drawCircle(x, y, ringWidth, endRingPaint);
            canvas.drawCircle(x, y, ringWidth/2, endCoverPaint);
        }

    }

    Paint ringPiant;
    Paint coverPaint;
    Paint endRingPaint;
    Paint endCoverPaint;
    int ringWidth = 20;
    Paint textPaint;
    Paint textPaint2;
    private void init(){
        ringPiant = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPiant.setStrokeWidth(ringWidth);
        ringPiant.setColor(Color.WHITE);
        ringPiant.setStyle(Paint.Style.STROKE);

        coverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        coverPaint.setStrokeWidth(ringWidth);
        coverPaint.setColor(getResources().getColor(R.color.color_pink_line));
        coverPaint.setStrokeCap(Paint.Cap.ROUND);
        coverPaint.setStyle(Paint.Style.STROKE);

        endRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        endRingPaint.setStyle(Paint.Style.FILL);
        endRingPaint.setColor(getResources().getColor(R.color.color_pink_line));

        endCoverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        endCoverPaint.setColor(Color.WHITE);
        endCoverPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.GRAY);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(DisplayUtil.sp2px(getContext(),16));

        textPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint2.setColor(getResources().getColor(R.color.color_pink_line));
        textPaint2.setStyle(Paint.Style.FILL);
        textPaint2.setTextSize(DisplayUtil.sp2px(getContext(),28));
        rect = new Rect();
    }


    float mPercnet = 0.35f;
    private String title1 = "运动消耗";
    private String title2 = "60";
    private String title3 = "kcal";

    public void setRingPercent(float x){
        mPercnet = x;
        playAnimation();


    }

    float aFloat= 0;
    private  void playAnimation(){
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float a = (float) animation.getAnimatedValue();
                aFloat = a;
                postInvalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }
}
