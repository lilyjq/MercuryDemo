package com.mercury.demo.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;

import com.mercury.demo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

public class TestVPActivity extends AppCompatActivity {


    FragmentTabHost tabHost;
    FrameLayout container;

    private int[] imageRes = {R.drawable.bia,R.drawable.bianzu,R.drawable.yuyue};
    private String[] tags = {"111","222","333"};
    private Class[] fragment ={ColorFragment.class,ColorPinkFragment.class,TestFragment.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testvp);
        tabHost = findViewById(R.id.tab);
        container = findViewById(R.id.container);
        tabHost.setup(this,getSupportFragmentManager(),R.id.container);
        for(int i=0;i<3;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tags[i]).setIndicator(getTabIndicator(imageRes[i]));
            tabHost.addTab(tabSpec,fragment[i],null);
        }
    }

    private View getTabIndicator(int resId) {
        final View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_host_item, null);
        final ImageView icon = view.findViewById(R.id.icon);
        icon.setImageResource(resId);
        return view;
    }
}
