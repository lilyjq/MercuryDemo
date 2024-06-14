package com.mercury.demo.behavior;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mercury.demo.R;

/**
 * @author: LJQ
 * @date: 2024/6/9 18:12
 */
public class TopBehavior extends LinearCoordinateLayout.BeHavior<View> {
  public TopBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }


  int maxHeight = 400;
  int originHeight = 0;


  RecyclerView recyclerView;
  private boolean init = true;

  private int scrollH = 0;

  private int lastDy = 0;

  @Override
  public boolean onLayoutChild(LinearCoordinateLayout parent, View child, int layoutDirection) {
    if (originHeight == 0) {
      originHeight = child.getHeight();
    }
    if(init) {
      recyclerView = parent.findViewById(R.id.recyclerview);
      recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          Log.i("jjjjjjjjjjjj", "onScrolled: "+dy);
          scrollH += dy;
          lastDy = dy;

        }
      });
      init = false;
    }



    return super.onLayoutChild(parent, child, layoutDirection);
  }

  @Override
  public void onNestedScroll(LinearCoordinateLayout coordinatorLayout, View child,
      View scrollview, int dxConsumed, int dyConsumed,
      int dxUnconsumed, int dyUnconsumed) {
    Log.i("eeeeee", "onNestedScroll: "+scrollH);
    if (scrollH/2.0 > 0) {
      LinearCoordinateLayout.LP lp = (LinearCoordinateLayout.LP) child.getLayoutParams();
      lp.height = (int) (lp.height - Math.abs(dyConsumed/2.0));
      if (lp.height <= 0) {
        lp.height = 0;
      }
      child.setLayoutParams(lp);
    } else if (scrollH/2.0 == 0) {
      LinearCoordinateLayout.LP lp = (LinearCoordinateLayout.LP) child.getLayoutParams();
      lp.height = (int) (lp.height + Math.abs(dyUnconsumed/2.0));
      if (lp.height >= originHeight) {
        lp.height = originHeight;
      }
      child.setLayoutParams(lp);
    }

  }

  @Override public void onStopNestedScroll(@NonNull View child, int type) {
    super.onStopNestedScroll(child, type);
    LinearCoordinateLayout.LP lp = (LinearCoordinateLayout.LP) child.getLayoutParams();
    int height = lp.height;
    Log.i("jjjjj", "onStopNestedScroll: "+height +"    "+lastDy  +"    "+scrollH);
    //if(height == 0 || height == originHeight){
    //}else{
      if(scrollH > 0 ){
        hideH(child,height);
      }else{
        showH(child,height,originHeight);
      }
    //}
  }


  private boolean isAnimate =false;


  private void hideH( View view,int height) {
    if(isAnimate){
      return;
    }
    ValueAnimator animator =  ValueAnimator.ofInt(height, 0);
    animator.setInterpolator(new DecelerateInterpolator());
    animator.setDuration(500);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

      @Override public void onAnimationUpdate(ValueAnimator animation) {
              ViewGroup.LayoutParams lp = view.getLayoutParams();
        //      //获取改变时的值
              int size = (int) animation.getAnimatedValue();
              lp.height = size;
              view.setLayoutParams(lp);
      }
    });
    animator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
        isAnimate = true;
      }

      @Override public void onAnimationEnd(Animator animation) {
        isAnimate = false;
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
     animator.start();
  }


  private void showH(View view,int nowHeight ,int height) {
    if(isAnimate){
      return;
    }
    ValueAnimator animator = ValueAnimator.ofInt(nowHeight, height);
    animator.setInterpolator(new DecelerateInterpolator());
    animator.setDuration(500);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        //      //获取改变时的值
        int size = (int) animation.getAnimatedValue();
        lp.height = size;
        view.setLayoutParams(lp);
      }
    });

    animator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
        isAnimate = true;
      }

      @Override public void onAnimationEnd(Animator animation) {
        isAnimate = false;
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });

    animator.start();
  }


}
