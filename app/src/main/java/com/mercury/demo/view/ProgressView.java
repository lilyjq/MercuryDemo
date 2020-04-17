package com.mercury.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.mercury.demo.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ProgressView extends View {
    private float mCircleRadius;
    private int mPaddingStart = 0;
    private int mPaddingEnd = 0;
    private int mPaddingTop = 0;
    private int mPaddingBottom = 0;


    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context,attrs);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    float mStroKeWidth;
    int mPColor,mBColor,mTColor;

    private void initTypeArray(Context context,AttributeSet attributeSet){
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressView);
        try {
            mStroKeWidth = ta.getDimension(R.styleable.ProgressView_stokeWidth,getResources().getDimension(R.dimen.circle_width));
            mPColor = ta.getColor(R.styleable.ProgressView_p_color,getResources().getColor(R.color.colorPrimary));
            mBColor = ta.getColor(R.styleable.ProgressView_b_color,getResources().getColor(R.color.colorPrimaryDark));
            mTColor = ta.getColor(R.styleable.ProgressView_t_color,getResources().getColor(R.color.textcolor));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
                ta.recycle();
        }

    }


    Paint mTestPaint = new Paint();
    Paint mBackgroundPaint = new Paint();
    Paint mForegroundPaint = new Paint();
    Paint mEndOutCiclePaint = new Paint();
    Paint mEndInnerCirclePaint = new Paint();
    Paint mTitleTextPaint = new Paint();
    Paint mValueTextPaint = new Paint();
    Paint mUnitTextPaint = new Paint();
    private int mPercent = 0;
    private int mTempPercent = 0;
    private int mViewWidth ;
    private int mViewHeight;
    private int mCenterX;
    private int mCenterY;

    private void initPaint(){

        mTestPaint.setColor(mPColor);
        mTestPaint.setAntiAlias(true);
        mTestPaint.setStyle(Paint.Style.STROKE);
        mTestPaint.setStrokeWidth(1);

        mForegroundPaint.setColor(mPColor);
        mForegroundPaint.setStrokeWidth(mStroKeWidth);
        mForegroundPaint.setStyle(Paint.Style.STROKE);
        mForegroundPaint.setStrokeCap(Paint.Cap.ROUND);
        mForegroundPaint.setAntiAlias(true);


        mBackgroundPaint.setColor(mBColor);
        mBackgroundPaint.setStrokeWidth(mStroKeWidth);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mBackgroundPaint.setAntiAlias(true);

        mEndOutCiclePaint.setColor(mBColor);
        mEndOutCiclePaint.setStyle(Paint.Style.FILL);
        mEndOutCiclePaint.setAntiAlias(true);

        mEndInnerCirclePaint.setColor(mBColor);
        mEndInnerCirclePaint.setStyle(Paint.Style.FILL);
        mEndInnerCirclePaint.setAntiAlias(true);

        mTitleTextPaint.setColor(mTColor);
        mTitleTextPaint.setTextSize(getResources().getDimension(R.dimen.default_text));
        mTitleTextPaint.setAntiAlias(true);
        mTitleTextPaint.setTextAlign(Paint.Align.CENTER);

        mValueTextPaint.setColor(mPColor);
        mValueTextPaint.setTextSize(getResources().getDimension(R.dimen.default_text));
        mValueTextPaint.setAntiAlias(true);
        mValueTextPaint.setTextAlign(Paint.Align.CENTER);

        mUnitTextPaint.setColor(mPColor);
        mUnitTextPaint.setTextSize(getResources().getDimension(R.dimen.default_text));
        mUnitTextPaint.setAntiAlias(true);
        mUnitTextPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int)getResources().getDimension(R.dimen.default_width),(int)getResources().getDimension(R.dimen.defalut_height));
        }else if(widthSpecMode == MeasureSpec.AT_MOST ){
            setMeasuredDimension((int)getResources().getDimension(R.dimen.default_width),heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,(int)getResources().getDimension(R.dimen.defalut_height));
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mPaddingStart = (int) (getPaddingStart()+mStroKeWidth/4);
        mPaddingEnd = (int) (getPaddingEnd()+mStroKeWidth/4);
        mPaddingTop = (int) (getPaddingTop()+mStroKeWidth/4);
        mPaddingBottom = (int) (getPaddingBottom()+mStroKeWidth/4);
        mViewWidth = getWidth() - mPaddingStart - mPaddingEnd;
        mViewHeight = getHeight() -mPaddingTop- mPaddingBottom;
        mCenterX = mPaddingStart+mViewWidth/2;
        mCenterY = mPaddingTop +mViewHeight/2;
        mCircleRadius = mViewWidth<mViewHeight? mViewWidth/2:mViewHeight/2;
        mFrameRectF.set(mCenterX-mCircleRadius,mCenterY-mCircleRadius,mCenterX+mCircleRadius,mCenterY+mCircleRadius);

    }

    RectF mFrameRectF = new RectF();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void drawBackgroudCircle(Canvas canvas){
        canvas.drawCircle(mCenterX,mCenterY,mCircleRadius-mStroKeWidth/2,mBackgroundPaint);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawForegroundCircle(Canvas canvas){
        canvas.drawArc(mFrameRectF.left+mStroKeWidth/2,mFrameRectF.top+mStroKeWidth/2,
                mFrameRectF.right-mStroKeWidth/2,mFrameRectF.bottom-mStroKeWidth/2,270, (float) (3.6*mTempPercent),false,mForegroundPaint);

        if(mTempPercent != 0 && mTempPercent != 100){
//            canvas.drawCircle((mCenterX+(mCircleRadius-mStroKeWidth/2)*Math.cos((3.6*mTempPercent-90)*Math.PI/180)),mCenterY+mCircleRadius-mStroKeWidth/2);
        }

    }
}
