package com.mercury.demo.aty;

import android.app.Activity;
import android.os.Bundle;

import com.mercury.demo.R;
import com.mercury.demo.view.PrgressView;

import androidx.annotation.Nullable;

public class ProgressActivity extends Activity {

    PrgressView prgressView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressview);
        prgressView2 = findViewById(R.id.ps2);
        prgressView2.setRingPercent(0.6f);
    }
}
