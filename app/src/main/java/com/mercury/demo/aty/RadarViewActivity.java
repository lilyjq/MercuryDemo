package com.mercury.demo.aty;

import android.app.Activity;
import android.os.Bundle;

import com.mercury.demo.R;
import com.mercury.demo.data.RaBean;
import com.mercury.demo.view.RadarView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class RadarViewActivity extends Activity {

    RadarView radarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        radarView = findViewById(R.id.radio);
        initRadarView();
    }


    private void initRadarView(){
        List<RaBean> list = new ArrayList<>();
        list.add(new RaBean(4,"简直了t"));
        list.add(new RaBean(5,"真厉害l1"));
        list.add(new RaBean(7,"233333"));
        list.add(new RaBean(3,"大力出奇迹3"));
        list.add(new RaBean(6,"不可思议4"));
        radarView.setList(list);

    }
}
