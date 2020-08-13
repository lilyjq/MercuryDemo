package com.mercury.demo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.mercury.demo.R;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoticeActivity extends Activity {
    int[] str = {R.string.test_transformPivot, R.string.text_test, R.string.tab4, R.string.tab3, R.string.tab1,R.string.tab6};
    ViewFlipper viewFlipper;
    TextSwitcher textSwitcher;
    private MyHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        viewFlipper = findViewById(R.id.viewflipper);
        textSwitcher = findViewById(R.id.textSwitcher);
        addViewFlipper();
        setTextSwitcher();
    }







    private void addViewFlipper() {
        viewFlipper.setInAnimation(this, R.anim.flip_in);
        viewFlipper.setOutAnimation(this, R.anim.flip_out);
        for (int i = 0; i < str.length; i++) {
            TextView textView = new TextView(this);
            textView.setTextSize(16);
            textView.setText(getResources().getString(str[i]));
            textView.setPadding(100,20,10,20);
            viewFlipper.addView(textView);
        }

        viewFlipper.startFlipping();
    }



    private void setTextSwitcher(){
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(NoticeActivity.this);
                textView.setPadding(100,20,10,20);
                return textView;
            }
        });
        textSwitcher.setInAnimation(this,R.anim.flip_in);
        textSwitcher.setOutAnimation(this,R.anim.flip_out);
        textSwitcher.setText(getResources().getString(str[0]));
        handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(1,3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }


    int nowPos = 0;
    public static  class MyHandler extends Handler{
        WeakReference<NoticeActivity> weakReference;

        public MyHandler(NoticeActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            if(weakReference.get() != null){
                NoticeActivity activity = weakReference.get();
                activity.changeTextSwitch();
            }
        }
    }

    private void changeTextSwitch(){
        nowPos = nowPos+1;
        if(nowPos == str.length){
            nowPos = 0;
        }
        textSwitcher.setText(getResources().getString(str[nowPos]));
        handler.sendEmptyMessageDelayed(1,3000);
    }
}
