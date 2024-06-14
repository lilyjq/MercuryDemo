package com.mercury.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author: LJQ
 * @date: 2023/12/1 10:34
 */
public class Mytestview extends androidx.appcompat.widget.AppCompatTextView {
  public Mytestview(@NonNull Context context) {
    super(context);
  }

  public Mytestview(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Mytestview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.i("ddddd", "onMeasure: "+getMeasuredWidth() +"   "+getMeasuredHeight());
    int wdithmode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
    Log.i("dddd", "onMeasure: "+wdithmode+ "      "+widthSize+"      "+heightMode+"         "+heightSize);
  }


}
