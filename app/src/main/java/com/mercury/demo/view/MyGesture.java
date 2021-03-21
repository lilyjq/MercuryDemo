package com.mercury.demo.view;

import android.view.GestureDetector;
import android.view.MotionEvent;

class MyGesture implements GestureDetector.OnGestureListener {
    //1 创建实例 OnGestureListener
    //2 创建 GestureDetector
    //3 在ontouch 中进行拦截

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
