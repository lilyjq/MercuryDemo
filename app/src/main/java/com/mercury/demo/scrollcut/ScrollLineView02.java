package com.mercury.demo.scrollcut;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mercury.demo.R;
import com.mercury.demo.scrollcut.ScrollCutView;
import com.mercury.demo.util.DisplayUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ScrollLineView02 extends FrameLayout {

    public ScrollLineView02(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_scroll_line,this);
    }

    private int lastX, lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int left = getLeft() + offsetX;
                int right = getRight()+offsetX;
                if(left<5){
                    layout(0, getTop() , getWidth(), getBottom());
                    return true;
                }
                if(right+5>=parentWidth){
                    layout(parentWidth-getWidth(),getTop(),parentWidth,getBottom());
                    return true;
                }
                layout(left, getTop() , right, getBottom());
                break;
            case MotionEvent.ACTION_UP:
//                if (scWidth - getRight() < getWidth()) {
//                    layout(scWidth - getWidth() / 2, getTop(), scWidth + getWidth() / 2, getBottom());
//                } else if (getLeft() < getWidth()) {
//                    layout(-getWidth() / 2, getTop(), getWidth() / 2, getBottom());
//                }
                break;
        }
        return true;
    }






    int parentWidth = (int) DisplayUtil.dp2px(250);
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!stopOnMeasture) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            final ViewGroup viewGroup = (ViewGroup) getParent();
            post(new Runnable() {
                @Override
                public void run() {
                    if (viewGroup != null) {
                        parentWidth = viewGroup.getWidth();
                    }
                }
            });
        }
    }

    ScrollCutView cutView;
    public void bindScrollCutView(ScrollCutView cutView){
        this.cutView = cutView;
    }

    private boolean stopOnMeasture = false;
    public void stopOnMeasure(boolean stopOnMeasture){
        this.stopOnMeasture = stopOnMeasture;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(!stopOnMeasture) {
            super.onLayout(changed, left, top, right, bottom);
        }
        if(changed){
            if(cutView != null){
                cutView.changeRect(right-getWidth()/2);
            }
            Log.e("onLayout changed",left+"");
            if(listener != null){
                listener.changLeft(left+getWidth()/2);
            }
        }
    }
    interface  ChangeListener{
        void changLeft(int left);
    }

    ChangeListener listener;
    public  void setChangeListener(ChangeListener listener){
          this.listener = listener;
    }


}
