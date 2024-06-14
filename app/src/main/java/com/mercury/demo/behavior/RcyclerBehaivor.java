package com.mercury.demo.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: LJQ
 * @date: 2024/6/10 14:28
 */
public class RcyclerBehaivor extends LinearCoordinateLayout.BeHavior<RecyclerView>{


  public RcyclerBehaivor(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onLayoutChild(LinearCoordinateLayout parent, RecyclerView child,
      int layoutDirection) {
    return super.onLayoutChild(parent, child, layoutDirection);
  }

  @Override public void onStopNestedScroll(@NonNull View child, int type) {
    super.onStopNestedScroll(child, type);
  }

  @Override public void onNestedScroll(LinearCoordinateLayout coordinatorLayout, RecyclerView child,
      View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
        dyUnconsumed);
  }
}
