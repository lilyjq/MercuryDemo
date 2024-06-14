package com.mercury.demo.behavior

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.mercury.demo.R

/**
 * @author: LJQ
 * @date:  2024/6/9 19:13
 */
class HomeRecyclerViewBehavior(context: Context?, attrs: AttributeSet?) :
  CoordinatorLayout.Behavior<View>(context, attrs) {

  private var topH = 0


  var scrollHeight = 0

var first = true

  override fun onLayoutChild(
    parent: CoordinatorLayout,
    child: View,
    layoutDirection: Int
  ): Boolean {

    parent.onLayoutChild(child,layoutDirection)
    if(first) {
      topH = parent.findViewById<View>(R.id.top)?.measuredHeight ?:0
      val  centerH = parent.findViewById<View>(R.id.tv_center)?.measuredHeight?:0
      val textH = parent.findViewById<View>(R.id.tv_text)?.measuredHeight ?:0
      scrollHeight = topH -centerH -textH
      first = false
    }
    //
    ViewCompat.offsetTopAndBottom(child,topH)
    return true
  }





  override fun onStartNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: View,
    directTargetChild: View,
    target: View,
    axes: Int,
    type: Int
  ): Boolean {
    return (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
  }

/*  override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
    return dependency.id == R.id.top
  }

  override fun onDependentViewChanged(
    parent: CoordinatorLayout,
    child: View,
    dependency: View
  ): Boolean {
     super.onDependentViewChanged(parent, child, dependency)

    topHeight = parent.findViewById<View>(R.id.top).measuredHeight
    Log.i("aaaa", "onDependentViewChanged: "+topHeight)
    child.translationY = topHeight.toFloat()
    return true


  }*/



  // var scroollH = 0
  override fun onNestedPreScroll(
    coordinatorLayout: CoordinatorLayout,
    child: View,
    target: View,
    dx: Int,
    dy: Int,
    consumed: IntArray,
    type: Int
  ) {
    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    Log.i("eeeeee", "onNestedPreScroll: $dy")

    if (dy > 0 && !isAnimate) {//往上
      val trans = child.translationY -dy
      if (trans >= -(scrollHeight)) {
        consumed[1] = dy
        child.translationY = trans
      }else{
        consumed[1] = (scrollHeight)+child.translationY.toInt()
        child.translationY = -(scrollHeight).toFloat()
      }
      state = -1 //收起
    }else if(!isAnimate){
      state = 1
    }
  }



  override fun onNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: View,
    target: View,
    dxConsumed: Int,
    dyConsumed: Int,
    dxUnconsumed: Int,
    dyUnconsumed: Int,
    type: Int,
    consumed: IntArray
  ) {
    super.onNestedScroll(
      coordinatorLayout,
      child,
      target,
      dxConsumed,
      dyConsumed,
      dxUnconsumed,
      dyUnconsumed,
      type,
      consumed
    )

    if(dyUnconsumed<0 && !isAnimate){
      val newTransY = child.translationY - dyUnconsumed
      if(newTransY <= 0){
        child.translationY = newTransY
      }else{
        child.translationY = 0f
      }

    }


  }


  private var state = 0


  override fun onStopNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: View,
    target: View,
    type: Int
  ) {
    super.onStopNestedScroll(coordinatorLayout, child, target, type)

    Log.i("eeeee", "onStopNestedScroll:  $state       ${child.translationY}   ")

    if((child.translationY == -scrollHeight.toFloat()) || (child.translationY == 0f)){

    }else {
      if (state == 1 && child.translationY < 0) {
        //下滑 展开 transY 变成0
        show(child)
      } else if (state == -1) {
        hide(child)
      }
    }
  }


  var isAnimate = false

  private fun hide(view: View) {
    if(isAnimate){
      return
    }
    val animator =
      view.animate().translationY(-scrollHeight.toFloat()).setInterpolator(ImageBtnBehavior.INTERPOLATOR)
        .setDuration(500)
    animator.setListener(object : AnimatorListener {
      override fun onAnimationStart(animation: Animator) {
        isAnimate = true
      }

      override fun onAnimationEnd(animation: Animator) {
        //view.setVisibility(View.INVISIBLE);
        isAnimate = false
      }

      override fun onAnimationCancel(animation: Animator) {
        show(view)
      }

      override fun onAnimationRepeat(animation: Animator) {}
    })
    animator.start()
  }

  private fun show(view: View) {
    if(isAnimate){
      return
    }
    val animator = view.animate().translationY(0f).setInterpolator(ImageBtnBehavior.INTERPOLATOR)
      .setDuration(500)
    animator.setListener(object : AnimatorListener {
      override fun onAnimationStart(animation: Animator) {
        view.visibility = View.VISIBLE
        isAnimate = true
      }

      override fun onAnimationEnd(animation: Animator) {
        isAnimate = false
      }

      override fun onAnimationCancel(animation: Animator) {
        // hide(view)
      }

      override fun onAnimationRepeat(animation: Animator) {}
    })
    animator.start()
  }


  // private fun hideH(view: View, height: Int) {
  //   if (isAnimate) {
  //     return
  //   }
  //   val animator = ValueAnimator.ofInt(height, 0)
  //   animator.interpolator = DecelerateInterpolator()
  //   animator.duration = 500
  //   animator.addUpdateListener(AnimatorUpdateListener { animation ->
  //     val lp = view.layoutParams
  //     //      //获取改变时的值
  //     val size = animation.animatedValue as Int
  //     lp.height = size
  //     view.layoutParams = lp
  //   })
  //   animator.addListener(object : AnimatorListener {
  //     override fun onAnimationStart(animation: Animator) {
  //       isAnimate = true
  //     }
  //
  //     override fun onAnimationEnd(animation: Animator) {
  //       isAnimate = false
  //     }
  //
  //     override fun onAnimationCancel(animation: Animator) {}
  //     override fun onAnimationRepeat(animation: Animator) {}
  //   })
  //   animator.start()
  // }
  //
  // private fun showH(view: View, nowHeight: Int, height: Int) {
  //   if (isAnimate) {
  //     return
  //   }
  //   val animator = ValueAnimator.ofInt(nowHeight, height)
  //   animator.interpolator = DecelerateInterpolator()
  //   animator.duration = 500
  //   animator.addUpdateListener(AnimatorUpdateListener { animation ->
  //     val lp = view.layoutParams
  //     //      //获取改变时的值
  //     val size = animation.animatedValue as Int
  //     lp.height = size
  //     view.layoutParams = lp
  //   })
  //   animator.addListener(object : AnimatorListener {
  //     override fun onAnimationStart(animation: Animator) {
  //       isAnimate = true
  //     }
  //
  //     override fun onAnimationEnd(animation: Animator) {
  //       isAnimate = false
  //     }
  //
  //     override fun onAnimationCancel(animation: Animator) {}
  //     override fun onAnimationRepeat(animation: Animator) {}
  //   })
  //   animator.start()
  // }


}