package com.mercury.demo.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.mercury.demo.R
import kotlin.math.roundToInt

/**
 * @author: LJQ
 * @date:  2024/6/9 19:18
 */
class HomeTopBehavior(context: Context?, attrs: AttributeSet?) :
  CoordinatorLayout.Behavior<View>(context, attrs) {
  //
  override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
    return dependency.id == R.id.recyclerview
  }

   var  topH = 0
   var centerH = 0
   var bottomH = 0

  var top:View? = null
  var center:View? = null
  var bottom :View? = null
  var scrollHeight = 0




  private var isFirst = true



  override fun onLayoutChild(
    parent: CoordinatorLayout,
    child: View,
    layoutDirection: Int
  ): Boolean {


    if(isFirst) {
      top = parent.findViewById<View>(R.id.tv_top)
      topH = top?.measuredHeight ?: 0
      center = parent.findViewById<View>(R.id.tv_center)
      centerH = center?.measuredHeight ?: 0
      bottom = parent.findViewById<View>(R.id.tv_bottom)
      bottomH = bottom?.measuredHeight ?: 0

      val allH = parent.findViewById<View>(R.id.top)?.measuredHeight ?:0
      scrollHeight = bottomH + topH




      isFirst = false
    }
    return super.onLayoutChild(parent, child, layoutDirection)
  }


  override fun onDependentViewChanged(
    parent: CoordinatorLayout,
    child: View,
    dependency: View
  ): Boolean {

    if(dependency.translationY<0 ){
      val y = if(dependency.translationY < -(topH +bottomH)){
        -(topH +bottomH).toFloat()
      }else{
        dependency.translationY
      }
      val ff = Math.abs(scrollHeight+y)/(scrollHeight)
      Log.i("ooooo", "onDependentViewChanged:  $ff")
      if(ff>=0 && ff<1) {
        val param = top!!.layoutParams
        param.height = (ff * topH).roundToInt()
        top!!.layoutParams = param

        val bparam = bottom!!.layoutParams
        bparam.height = (ff * bottomH).roundToInt()
        bottom!!.layoutParams = bparam
      }
    }else{
      val param = top!!.layoutParams
      param.height = (topH)
      top!!.layoutParams = param

      val bparam = bottom!!.layoutParams
      bparam.height = (bottomH)
      bottom!!.layoutParams = bparam
    }

    // lastY = dependency.translationY
    Log.i("eeeeee", "onDependentViewChanged:   "+dependency.translationY +"         " +(scrollHeight) )
    return super.onDependentViewChanged(parent, child, dependency)
  }

  override fun onStopNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: View,
    target: View,
    type: Int
  ) {
    super.onStopNestedScroll(coordinatorLayout, child, target, type)
    Log.i("eeeee", "onStopNestedScroll: ")

  }

  var scrollH = 0
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
    Log.i("ppppp", "onNestedPreScroll: "+dy)
    if (dy > 0) {//往上
      // scrollH += dy
      // if(scrollH <= (scrollHeight)){
      //
      //   val ff = Math.abs(topH + bottomH-scrollH) * 1.0 / (topH + bottomH)
      //   val param = top!!.layoutParams
      //   param.height = (ff * topH).toInt()
      //   top!!.layoutParams = param
      //
      //   val bparam = bottom!!.layoutParams
      //   bparam.height = (ff * bottomH).toInt()
      //   bottom!!.layoutParams = bparam
      // }
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
    Log.i("ppp", "onNestedScroll: "+dyConsumed +"     "+dyUnconsumed)
  }














}