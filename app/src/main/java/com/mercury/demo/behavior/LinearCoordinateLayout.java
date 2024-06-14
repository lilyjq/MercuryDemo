package com.mercury.demo.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.mercury.demo.R;
import java.lang.reflect.Constructor;

/**
 * @author: LJQ
 * @date: 2024/6/9 16:11
 *https://blog.csdn.net/eyishion/article/details/121436522
 *
 */
public class LinearCoordinateLayout extends LinearLayout implements NestedScrollingParent2,NestedScrollingParent3,
    ViewTreeObserver.OnGlobalLayoutListener {
  public LinearCoordinateLayout(Context context) {
    super(context);

  }

  public LinearCoordinateLayout(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    getViewTreeObserver().addOnGlobalLayoutListener(this);
  }

  public LinearCoordinateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public LinearCoordinateLayout(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override public void onGlobalLayout() {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View v = getChildAt(i);
      LP lp = (LP) v.getLayoutParams();
      BeHavior mBehavior = lp.mBehavior;
      if (mBehavior != null) {
        mBehavior.onLayoutChild(this, v, 0);
      }
    }


  }

  @Override
  public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed, int type, @NonNull int[] consumed) {

    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View v = getChildAt(i);
      LP lp = (LP) v.getLayoutParams();
      BeHavior mBehavior = lp.mBehavior;
      if (mBehavior != null) {
        mBehavior.onNestedScroll(this, v, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
      }
    }


  }

  @Override public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes,
      int type) {
    return true;
  }

  @Override public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes,
      int type) {

  }

  @Override public void onStopNestedScroll(@NonNull View target, int type) {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View v = getChildAt(i);
      LP lp = (LP) v.getLayoutParams();
      BeHavior mBehavior = lp.mBehavior;
      if (mBehavior != null) {
        mBehavior.onStopNestedScroll(v,type);
      }
    }
  }

  @Override
  public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed, int type) {

  }

  @Override
  public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed,
      int type) {

  }



  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new LP(getContext(),attrs);
  }

  public static class LP extends LinearLayout.LayoutParams {

    BeHavior mBehavior;

    public LP(Context c, AttributeSet attrs) {
      super(c, attrs);
      TypedArray ta = null;
      try {

         ta = c.obtainStyledAttributes(attrs, R.styleable.LinearCoordinateLayout);

        if (ta.hasValue(R.styleable.LinearCoordinateLayout_custom_layout_behavior)) {
          String clStr = ta.getString(R.styleable.LinearCoordinateLayout_custom_layout_behavior);
          Log.e("tag", clStr);
          mBehavior = parseBehavior(c, attrs, clStr);
          Log.e("tag", (mBehavior == null) + " ");
        }
      }catch (Exception e){

      }finally {
        if(ta!= null) {
          ta.recycle();
        }
      }

    }


    public BeHavior parseBehavior(Context context, AttributeSet attrs, String name) {
      try {
        Class<?> clazz = Class.forName(name, false, context.getClassLoader());
        Constructor c = clazz.getConstructor(Context.class, AttributeSet.class);
        c.setAccessible(true);
        return (BeHavior) c.newInstance(context, attrs);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }


  }


  public static class BeHavior<V extends View> {


    public BeHavior(Context context, AttributeSet attrs) {
    }

    public boolean onLayoutChild(LinearCoordinateLayout parent, V child,
        int layoutDirection) {
      return false;
    }


    public void onNestedScroll(LinearCoordinateLayout coordinatorLayout, V child,
        View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
        int dyUnconsumed) {
    }


   public  void onStopNestedScroll(@NonNull View child, @ViewCompat.NestedScrollType int type){


   }

  }


}
