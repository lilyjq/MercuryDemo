package com.mercury.demo.scrollcut;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: LJQ
 * @date: 2023/12/3 10:02
 */
public class CenterLayoutManager extends LinearLayoutManager {
  CenterSmoothScroller smoothScroller;


  public CenterLayoutManager(Context context) {
    super(context);
  }

  public CenterLayoutManager(Context context, int orientation, boolean reverseLayout) {
    super(context, orientation, reverseLayout);
  }

  public CenterLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  /**
   * Recycler 滚动调用的方法，重写这个方法
   * @author hechunhui
   * @time 2022/1/20 14:49
   */
  @Override
  public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
    if(smoothScroller == null) {
      smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
    }
    smoothScroller.setTargetPosition(position);
    startSmoothScroll(smoothScroller);
  }

  @Override public void scrollToPosition(int position) {
    super.scrollToPosition(position);
  }

  private static class CenterSmoothScroller extends LinearSmoothScroller {

    CenterSmoothScroller(Context context) {
      super(context);
    }

    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
      return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
      //定位距离  这里显示的是界面中央
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        Log.i("VoiceVoice", "calculateSpeedPerPixel: "+( 800f /displayMetrics.densityDpi));
        return 800f / displayMetrics.densityDpi;
      //滚动速度，数字越小，速度越快
    }
  }

}
