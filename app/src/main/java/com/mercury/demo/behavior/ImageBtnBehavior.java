package com.mercury.demo.behavior;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class ImageBtnBehavior extends CoordinatorLayout.Behavior<View> {
     static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private float viewY;//控件距离coordniatorLayout底部的距离
    private boolean isAnimate;//动画是否正在进行






    public ImageBtnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当CoordinatorLayout的后代尝试启动嵌套滚动时调用。
     * 在嵌套滑动开始前回调
     */

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {

        if(child.getVisibility() == View.VISIBLE && viewY == 0){
            viewY = coordinatorLayout.getHeight()-child.getY();
        }
      return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0; //判断是否竖直滚动
    }

    /**
     * 当目标正在消耗任何滚动距离之前，正在进行的嵌套滚动将要更新时调用。
     * 在嵌套滑动进行时，对象消费滚动距离前回调
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//        Log.e("aaa","onNestedPreScroll : dy      "+dy+"        isAnimate:         "+isAnimate+"         childviesible:       " +(child.getVisibility()==View.VISIBLE));
        if(dy>0 && !isAnimate && child.getVisibility() == View.VISIBLE){
            hide(child);
            //hideRight(child);
        }else if(dy<0 && !isAnimate && child.getVisibility() == View.INVISIBLE){
            show(child);
            //showLeft(child);
        }
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
        @NonNull View child,
        @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }

    private void hide(final View view){
        ViewPropertyAnimator animator = view.animate().translationY(-viewY).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //view.setVisibility(View.INVISIBLE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                     show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }


    private void show(final View view){
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
               isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                     hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();

    }

    private void showLeft(final View view){
        ViewPropertyAnimator animator = view.animate().translationX(0).setInterpolator(INTERPOLATOR).setDuration(1000);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();

    }

    private void hideRight(final  View view){
        ViewPropertyAnimator animator = view.animate().translationX(view.getMeasuredWidth()).setInterpolator(INTERPOLATOR).setDuration(1000);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimate = true;
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
               showLeft(view);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

}
