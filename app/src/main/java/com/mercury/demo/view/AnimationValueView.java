package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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

  private int realLength;


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
    canvas.drawLine(padding,getHeight()/2,rightLength,getHeight()/2,defaultLinePaint);

    if(rightScrollReady){
      //drawLeft(canvas);
      drawRight(canvas);
    }else{
      drawRight(canvas);
      //drawLeft(canvas);
    }





  }

  float offset = DisplayUtil.dp2px(1);
  private boolean lastIsLeft ;
  private void drawLeft(Canvas canvas){
    //canvas.drawLine(mStart,getHeight()/2f,leftLength-offset,getHeight()/2f,leftLinePaint);
    canvas.drawCircle(padding,getHeight()/2f,30,leftBallPaint);
  }

  private void drawRight(Canvas canvas){
    //canvas.drawLine(mEnd,getHeight()/2.f,rightLength+offset,getHeight()/2f,rightLinePaint);
    canvas.drawCircle(rightLength,getHeight()/2f,30,rightBallPaint);
  }



  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mEnd = getContext().getResources().getDisplayMetrics().widthPixels-padding;

    final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    final int heightSize = MeasureSpec.getSize(widthMeasureSpec);
    int hopeWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize,widthMode);
    int hopeHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,heightMode);
    if(hopeWidth>0){
      hopeWidthMeasureSpec = hopeWidth;
    }
    setMeasuredDimension(hopeWidthMeasureSpec,hopeHeightMeasureSpec);


    if(rightLength == 0){
      rightLength = hopeWidth;
      mRightPos = hopeWidth;
    }

  }

  float padding = DisplayUtil.dp2px(40);


  int leftLength = (int) padding;
  int rightLength = 0;

  float mStart = padding;

  float mEnd;


  float mLeftPos = padding;
  float mRightPos;

  boolean leftScrollReady;
  boolean rightScrollReady;

  private int lastX;

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
           lastX = (int) x;
         }else{
           return false;
         }

        break;
      case MotionEvent.ACTION_MOVE:
         if(leftScrollReady){
           if( x <=rightLength && x>=mStart){
               leftLength = (int) x;
               lastX = (int) x;
               postInvalidate();
           }

         }else if(rightScrollReady){
            if(x>=leftLength && x<mEnd ){
              rightLength = (int) ((int) rightLength -(lastX-x));
              lastX = (int) x;
              //加一个误差
            }else if(x>= leftLength && x>mEnd+DisplayUtil.dp2px(5)){
              if(callback!= null){
                callback.onScroll((int) (getMeasuredWidth()-x));
              }
              rightLength = (int) (rightLength+lastX-x);

              lastX = (int) x;
              //leftLength = (int) (leftLength +(x-getMeasuredWidth()));
            }else if(x>= leftLength  && x<=mStart+DisplayUtil.dp2px(5)) {

              //int tmp = leftLength- ((int) x - getMeasuredWidth());

              //if(tmp > mStart){
                if (callback != null) {
                  callback.onScroll((int) x - getMeasuredWidth());
                }
                //leftLength = tmp;
              }
            //}
            postInvalidate();
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


  public onScrollCallback callback;

  public void setCallback(onScrollCallback callback) {
    this.callback = callback;
  }

 int  recyclerViewleft = 0;
  public void setRecyclerLeft(int left){
    //leftLength = (int) (recyclerViewleft+padding);
    //postInvalidate();
  }

 public interface onScrollCallback{
    void onScroll (int x);
  }


  int hopeWidth = (int) DisplayUtil.dp2px(300);
  public  void setHopeWidth(int hopeWidth){
    this.hopeWidth = hopeWidth;
  }


}
