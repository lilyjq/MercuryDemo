package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mercury.demo.R;
import com.mercury.demo.util.DisplayUtil;

/**
 * @author: LJQ
 * @date: 2023/4/25 5:18 PM
 */
public class AnimationValueView extends View {




  public AnimationValueView(@NonNull Context context) {
    super(context);
    init();
  }

  public AnimationValueView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public AnimationValueView(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public AnimationValueView(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  Paint  defaultLinePaint;
  Paint  leftLinePaint;
  Paint  leftBallPaint;
  Paint  rightLinePaint;
  Paint  rightBallPaint;

  int maxLength;

  //float precent = 0f;
  //float rightPrecent = 0f;




  private void init(){

    defaultLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    defaultLinePaint.setStrokeWidth(20);
    defaultLinePaint.setColor(Color.GRAY);
    defaultLinePaint.setStyle(Paint.Style.STROKE);
    defaultLinePaint.setStrokeCap(Paint.Cap.ROUND);

    leftLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    leftLinePaint.setStrokeWidth(20);
    leftLinePaint.setColor(getResources().getColor(R.color.color_pink_line));
    leftLinePaint.setStrokeCap(Paint.Cap.ROUND);
    leftLinePaint.setStyle(Paint.Style.STROKE);

    leftBallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    leftBallPaint.setStyle(Paint.Style.FILL);
    leftBallPaint.setColor(getResources().getColor(R.color.color_pink_line));


    rightLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    rightLinePaint.setStrokeWidth(20);
    rightLinePaint.setColor(getResources().getColor(R.color.color_yellow_line));
    rightLinePaint.setStrokeCap(Paint.Cap.ROUND);
    rightLinePaint.setStyle(Paint.Style.STROKE);

    rightBallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    rightBallPaint.setStyle(Paint.Style.FILL);
    rightBallPaint.setColor(getResources().getColor(R.color.color_yellow_line));

  }




  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    //canvas.drawLine(30f, top, (getWidth()-30),bottom,defaultLinePaint);
    canvas.drawLine(mStart,getHeight()/2,mEnd,getHeight()/2,defaultLinePaint);

    if(rightScrollReady){
      drawLeft(canvas);
      drawRight(canvas);
    }else{
      drawRight(canvas);
      drawLeft(canvas);
    }


  }

  float offset = DisplayUtil.dp2px(1);
  private boolean lastIsLeft ;
  private void drawLeft(Canvas canvas){
    canvas.drawLine(mStart,getHeight()/2f,leftLength-offset,getHeight()/2f,leftLinePaint);
    canvas.drawCircle(leftLength-offset,getHeight()/2f,30,leftBallPaint);
  }

  private void drawRight(Canvas canvas){
    canvas.drawLine(mEnd,getHeight()/2.f,rightLength+offset,getHeight()/2f,rightLinePaint);
    canvas.drawCircle(rightLength+offset,getHeight()/2f,30,rightBallPaint);
  }



  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mEnd = getMeasuredWidth()-padding;
    maxLength = getWidth()-200;
    if(rightLength == 0){
      rightLength = (int) mEnd;
    }

  }

  float padding = DisplayUtil.dp2px(16);


  int leftLength = (int) padding;
  int rightLength = 0;

  float mStart = padding;
  float mEnd;

  boolean leftScrollReady;
  boolean rightScrollReady;

  @Override public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    float x = event.getX();
    float y = event.getY();

    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
         boolean downY = Math.abs(getHeight()/2.0f-y)<DisplayUtil.dp2px(16);
         boolean leftX = Math.abs(leftLength-x)<DisplayUtil.dp2px(16);
         boolean rightX = Math.abs(rightLength-x)<DisplayUtil.dp2px(16);
         if(leftX && rightX){
           if(lastIsLeft){
             rightX = false;
           }else{
             leftX = false;
           }
         }
         if(downY && leftX){
           leftScrollReady = true;
           lastIsLeft = true;
         }else if(downY && rightX){
           rightScrollReady = true;
           lastIsLeft = false;
         }

        break;
      case MotionEvent.ACTION_MOVE:
         if(leftScrollReady){
           if( x <=rightLength && x>=mStart){
               leftLength = (int) x;
               postInvalidate();
           }

         }else if(rightScrollReady){
            if(x>=leftLength && x<mEnd){
              rightLength = (int) x;
              postInvalidate();
            }
         }
        break;


      case MotionEvent.ACTION_UP:
        leftScrollReady = false;
        rightScrollReady = false;
        break;

      default:
        break;
    }
    return true;
  }





}
