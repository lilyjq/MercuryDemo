package com.mercury.demo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.mercury.demo.R;

import androidx.annotation.Nullable;

public class NoticeActivity extends Activity {

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        viewFlipper = findViewById(R.id.viewflipper);
        addViewFlipper();
    }

    int[] str = {R.string.test_transformPivot, R.string.text_test, R.string.tab4, R.string.tab3, R.string.text_test};

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
}
