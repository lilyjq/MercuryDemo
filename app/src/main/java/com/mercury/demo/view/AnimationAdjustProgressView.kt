package com.mercury.demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.mercury.demo.R

/**
 * @author: LJQ
 * @date:  2023/4/25 3:51 PM
 */
public class AnimationAdjustProgressView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


  private lateinit var defaultLinePaint:Paint
  private lateinit var leftLinePaint:Paint
  private lateinit var leftBallPaint: Paint
  private lateinit var rightLinePaint: Paint
  private lateinit var rightBallPaint:Paint



  init {
     // LayoutInflater.from(getContext()).inflate(R.layout.layout_animation_adjust_progress,this)


    defaultLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    defaultLinePaint.color = Color.CYAN
    defaultLinePaint.style = Paint.Style.STROKE
    defaultLinePaint.strokeWidth = 20f

    leftLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    leftLinePaint.color = getContext().resources.getColor(R.color.color_pink_line)
    leftLinePaint.style =  Paint.Style.STROKE
    leftLinePaint.strokeCap = Paint.Cap.ROUND
    leftBallPaint.strokeWidth = 20f

    leftBallPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    leftBallPaint.color = getContext().resources.getColor(R.color.color_pink_line)
    leftBallPaint.style =  Paint.Style.FILL



    rightLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    rightLinePaint.color = getContext().resources.getColor(R.color.color_yellow_line)
    rightLinePaint.style =  Paint.Style.STROKE
    rightLinePaint.strokeCap = Paint.Cap.ROUND
    rightLinePaint.strokeWidth = 20f

    rightBallPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    rightBallPaint.color = getContext().resources.getColor(R.color.color_yellow_line)
    rightBallPaint.style =  Paint.Style.FILL

  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    var top = height/2-10
    var bottom = height/2+10
    canvas?.drawLine(30f, top.toFloat(), (width-30).toFloat(), bottom.toFloat(),defaultLinePaint)


  }





}