package com.mercury.demo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mercury.demo.R;
import com.mercury.demo.data.RaBean;
import com.mercury.demo.util.DisplayUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class RadarView extends View {

    private Paint paint;
    private Paint pointPaint;
    private Paint coverPaint;
    private int size = 5;
    private int lineSize = 5;

    private CornerPathEffect effect;
    private Paint textpaint;
    private int max = 10;

    private int centerX ;
    private int centerY ;
    private int raduis;
    private int distance = (int) DisplayUtil.dp2px(30)*2;
    private Path path;





    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        typeArray(context,attrs);
        init(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typeArray(context,attrs);
        init(context);
    }

    private int textSize;
    private int textColor;
    private int lineColor;
    private int coverColor;
    private float roundSize;

    private void typeArray(Context context, @Nullable AttributeSet attrs){
        TypedArray typedArray =context.obtainStyledAttributes(attrs,R.styleable.RadarView);
        textSize = (int) typedArray.getDimension(R.styleable.RadarView_textSize,16);
        textColor = typedArray.getColor(R.styleable.RadarView_textColor,Color.GRAY);
        lineColor = typedArray.getColor(R.styleable.RadarView_lineColor,getResources().getColor(R.color.color_pink_line));
        coverColor = typedArray.getColor(R.styleable.RadarView_coverColor,getResources().getColor(R.color.color_pink));
        size = typedArray.getInt(R.styleable.RadarView_defalutSize,5);
        lineSize = typedArray.getInt(R.styleable.RadarView_lineSize,5);
        roundSize = typedArray.getFloat(R.styleable.RadarView_lineRound,30);
        typedArray.recycle();

    }

    private void init(Context context){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(lineColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);

        coverPaint = new Paint();
        coverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        coverPaint.setColor(coverColor);
        coverPaint.setAntiAlias(true);
        coverPaint.setStrokeWidth(5);

        pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(context.getResources().getColor(R.color.color_yellow));
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(20);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        effect = new CornerPathEffect(roundSize);
        coverPaint.setPathEffect(effect);
        paint.setPathEffect(effect);

        textpaint = new Paint();
        textpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textpaint.setStrokeWidth(2);
        textpaint.setColor(textColor);
        textpaint.setTextSize(40);

        path = new Path();
        coverPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(DisplayUtil.sp2px(getContext(),220),DisplayUtil.sp2px(getContext(),220));
        }else if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(DisplayUtil.sp2px(getContext(),220),heightSize);
        }else if(getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(widthSize,DisplayUtil.sp2px(getContext(),220));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        int width = getWidth()-paddingStart-paddingEnd;
        int height  = getHeight()-paddingTop-paddingBottom;
         centerX = width/2;
         centerY = height/2;
         raduis = width>height? height/2-distance :width/2-distance;
        for(int i = 1;i<lineSize+1;i++){
            int radui = raduis/lineSize*i;
            drawBase(canvas,radui);
        }

        if(list != null) {
            drawCover(canvas);
        }

    }

    Path coverPath;

    private void drawCover(Canvas canvas){
        coverPath.reset();
            for (int i = 0; i < size; i++) {
                //弧长等于半径的弧,其所对的圆心角为1弧度，而一个圆的周长为2πr，其对应的弧度数为2πr/r=2π（即360°
                //弧度和角度的换算关系如下：
                //1弧度=180/π度
                //1度=π/180弧度
                //也就是说，180度=π 弧度
                double angle = 2*Math.PI/size*i;
                double a = 1.0;
                double precent = list.get(i).point*a / max*a*mCurF;
                float x = (float) (centerX + raduis * Math.sin(angle) * precent);
                float y = (float) (centerY - raduis * Math.cos(angle) * precent);
//                if(i == 0)
//                canvas.drawPoint(x, y, coverPaint);
                int radui = raduis/lineSize*6;
                float xt = (float) (centerX + (raduis+10) * Math.sin(angle));
                float xy = (float) (centerY - (raduis+10) * Math.cos(angle));
//                coverPaint.setColor(getResources().getColor(R.color.color_yellow));
                canvas.drawPoint(xt,xy,pointPaint);
                drawText(canvas,angle,xt,xy,i);
                if (i == 0) {
                    coverPath.moveTo(x, y);
                } else {
                    coverPath.lineTo(x, y);
                }
            }

            coverPath.close();
            canvas.drawPath(coverPath, coverPaint);

    }


    private void drawText(Canvas canvas,double angle,float x,float y,int i){
     /*   int tempI = 0;
         for(int i= 0;i<list.size()-2;i++){
             String forword = list.get(i).name;
             String backword = list.get(i+1).name;
             if(forword.length()>backword.length()){
                 tempI = i;
             }else{
                 tempI = i+1;
             }
         }
        String maxLengthStr = list.get(tempI).name;*/
        String str = list.get(i).name;
        double ang1 = Math.PI / 180 ;
        double ang2 = Math.PI / 180 * 170;
        double ang3 = Math.PI / 180 * 200;
        double ang4 = Math.PI / 180 * 350;
        double ang5 = Math.PI / 180 * 140;
        double ang6 = Math.PI / 180 * 220;
       if(ang1<angle && angle<ang2 ){
           textpaint.setTextAlign(Paint.Align.LEFT);
       }else if(ang2 <angle && angle<ang3){
           textpaint.setTextAlign(Paint.Align.CENTER);
       }else if(ang3<angle && angle<ang4){
           textpaint.setTextAlign(Paint.Align.RIGHT);
       }else{
           textpaint.setTextAlign(Paint.Align.CENTER);
       }

        if(ang5<angle && angle<ang6 ){
            y = y+50;
        }

       canvas.drawText(str,x,y,textpaint);

    }

    List<RaBean> list;


    public void setList(List<RaBean> list){
        this.list = list;
        size = list.size();
        palyAnimation();
//        postInvalidate();
    }


    /*
    绘制底部的线条
     */
    private void drawBase(Canvas canvas,int raduis){
        path.reset();
        paint.setPathEffect(effect);
        for(int i= 0;i<size;i++){
            double angle = 2*Math.PI/size*i;
            float x = (float) (centerX+raduis*Math.sin(angle));
            float y = (float) (centerY-raduis*Math.cos(angle));
            if(i == 0) {
                path.moveTo(x, y);
            }else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path,paint);
    }


    private float mCurF = 0;

    private void palyAnimation(){
        ValueAnimator animator = ValueAnimator.ofFloat(0.f,1.f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                  mCurF = (float) animation.getAnimatedValue();
                  postInvalidate();
            }
        });

        animator.start();
    }

}
