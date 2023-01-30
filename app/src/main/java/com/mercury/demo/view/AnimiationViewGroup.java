package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mercury.demo.R;
import com.mercury.demo.util.DisplayUtil;

/**
 * @author: LJQ
 * @date: 2023/4/25 6:17 PM
 */
public class AnimiationViewGroup extends FrameLayout {


  private View leftBall;
  private View rightBall;
  public AnimiationViewGroup(@NonNull Context context) {
    super(context);
    init();
  }

  public AnimiationViewGroup(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public AnimiationViewGroup(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public AnimiationViewGroup(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  int lastX ;
  int lastY;
  private void init(){
    LayoutInflater.from(getContext()).inflate(R.layout.layout_animation_adjust_progress,this);
    leftBall = findViewById(R.id.left_ball);
    rightBall = findViewById(R.id.right_ball);

    leftBall.setOnTouchListener(new OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {

        return false;
      }
    });
    //出于效率的考虑，ViewGroup 默认会绕过 draw() 方法，换而直接执行 dispatchDraw()，以此来简化绘制流程。
    //setWillNotDraw(false); 切换到完整的draw流程
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    //获取到手指处的横坐标和纵坐标
    int x = (int) event.getX();
    int y = (int) event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        lastX = x;
        lastY = y;
        break;
      case MotionEvent.ACTION_MOVE:
        int offsetX = x - lastX;
        int left = getLeft() + offsetX;
        int right = getRight()+offsetX;
        if(left<5){
          layout(0, getTop() , getWidth(), getBottom());
          return true;
        }
        if(right>=getWidth()){
          layout((int) (getWidth()- DisplayUtil.dp2px(16)),getTop(),getWidth(),getBottom());
          return true;
        }
        layout(left, getTop() , right, getBottom());
        break;
      case MotionEvent.ACTION_UP:
        //                if (scWidth - getRight() < getWidth()) {
        //                    layout(scWidth - getWidth() / 2, getTop(), scWidth + getWidth() / 2, getBottom());
        //                } else if (getLeft() < getWidth()) {
        //                    layout(-getWidth() / 2, getTop(), getWidth() / 2, getBottom());
        //                }
        break;
    }


    return super.onTouchEvent(event);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override public void draw(Canvas canvas) {
    super.draw(canvas);
  }
}
