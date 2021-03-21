package com.mercury.demo.view;

import android.view.GestureDetector;
import android.view.MotionEvent;

class SimpleGestureListener  extends GestureDetector.SimpleOnGestureListener {

    final int FILING_MIN_DISTANCE = 100,FILING_MIN_VELOCITY =  200;

    /**
     *
     * @param e1 第一个aciondown 饿MotionEvent
     * @param e2 最后一个ACTION_DOWN的MotionEvent
     * @param velocityX x轴上移动的速度，单位为像素/秒
     * @param velocityY y轴上移动的速度，单位为像素/秒
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if(e1.getX()-e2.getX()>FILING_MIN_DISTANCE && Math.abs(velocityX)>FILING_MIN_VELOCITY){
            //左滑
        }else if(e2.getX()-e1.getX()>FILING_MIN_DISTANCE && Math.abs(velocityX)>FILING_MIN_VELOCITY){
            //右滑
        }


        return true;
    }
}
