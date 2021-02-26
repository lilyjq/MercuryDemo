package com.mercury.demo.aty;

import android.os.Bundle;

import com.mercury.demo.R;
import com.mercury.demo.view.PathView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PathActivity extends AppCompatActivity {


    PathView pathView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_touch_path);
//        setContentView(R.layout.activity_path);
//        pathView = findViewById(R.id.pathView);
//        pathView.startAnimation(0);
    }
}
