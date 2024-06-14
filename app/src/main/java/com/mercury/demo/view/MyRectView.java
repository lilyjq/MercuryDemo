package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.mercury.demo.util.DisplayUtil;

/**
 * @author: LJQ
 * @date: 2023/8/31 15:09
 */
public class MyRectView extends View {

  Paint paint = new Paint();
  Paint slidePaint = new Paint();

  int padding = (int) DisplayUtil.dp2px(20);


  public MyRectView(Context context) {
    super(context);
    init();
  }

  public MyRectView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public MyRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init(){
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.BLUE);
    paint.setStrokeWidth(DisplayUtil.dp2px(2));

    slidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    slidePaint.setColor(Color.RED);
    slidePaint.setStrokeWidth(DisplayUtil.dp2px(10));

  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawLine(padding,0,padding,getMeasuredHeight(),slidePaint);

    canvas.drawLine(getMeasuredWidth()-padding,0,getMeasuredHeight()-padding,getMeasuredHeight(),slidePaint);


    //canvas.drawLine();

  }
}
