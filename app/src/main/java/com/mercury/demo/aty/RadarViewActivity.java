package com.mercury.demo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mercury.demo.R;
import com.mercury.demo.data.RaBean;
import com.mercury.demo.view.RadarView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class RadarViewActivity extends Activity implements View.OnClickListener {

    RadarView radarView;
    TextView mMinusTv;
    TextView mPlusTv;
    List<RaBean> list;
    List<RaBean> datalist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        radarView = findViewById(R.id.radio);
        mMinusTv = findViewById(R.id.tv_minus);
        mPlusTv = findViewById(R.id.tv_plus);
        mMinusTv.setOnClickListener(this);
        mPlusTv.setOnClickListener(this);
        list = new ArrayList<>();
        datalist = new ArrayList<>();
        initRadarView();
    }


    private void initRadarView(){
        list.add(new RaBean(4,"简直了"));
        list.add(new RaBean(5,"真厉害"));
        list.add(new RaBean(7,"233333"));
        list.add(new RaBean(8,"大力出奇迹"));
        list.add(new RaBean(6,"不可思议"));
        list.add(new RaBean(5,"奇奇怪怪"));
        list.add(new RaBean(9,"可可爱爱"));

        datalist.add(new RaBean(4,"简直了"));
        datalist.add(new RaBean(5,"真厉害"));
        datalist.add(new RaBean(7,"233333"));
        datalist.add(new RaBean(8,"大力出奇迹"));
        datalist.add(new RaBean(6,"不可思议"));
        datalist.add(new RaBean(5,"奇奇怪怪"));
        datalist.add(new RaBean(9,"可可爱爱"));
        radarView.setList(list);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tv_plus){
            if(list.size()>= datalist.size()){
                Toast.makeText(this,"max",Toast.LENGTH_SHORT).show();
            }else{
                list.add(datalist.get(list.size()));
                radarView.setList(list);
            }

        }else if(id == R.id.tv_minus){
            if(list.size()==3 ){
                Toast.makeText(this,"min",Toast.LENGTH_SHORT).show();
            }else{
                list.remove(list.size()-1);
                radarView.setList(list);
            }
        }
    }
}
