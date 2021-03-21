package com.mercury.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

class MyLinearLayout extends ViewGroup {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        int widht = 0;
        int count = getChildCount();
        for(int i = 0;i<count;i++){
            View view  = getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            int childHeight = view.getMeasuredHeight()+params.topMargin+params.bottomMargin;
            int childWidht = view.getMeasuredWidth()+params.leftMargin+params.rightMargin;
            height+=childHeight;
            widht = Math.max(widht,childWidht);
        }
        setMeasuredDimension(((widthMode ==MeasureSpec.EXACTLY)?measureWidth:widht),((height ==MeasureSpec.EXACTLY)?measureHeight:height));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top  = 0;
        int count = getChildCount();
        for(int i = 0;i<count;i++) {
            View view = getChildAt(i);
//            if(view.getLayoutParams() instanceof MarginLayoutParams){
//
//            }
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            int childHeight = view.getMeasuredHeight()+params.topMargin+params.bottomMargin;
            int childWidht = view.getMeasuredWidth()+params.leftMargin+params.rightMargin;
            view.layout(0,top,childWidht,top+childHeight);
            top+= childHeight;
        }

    }
}
