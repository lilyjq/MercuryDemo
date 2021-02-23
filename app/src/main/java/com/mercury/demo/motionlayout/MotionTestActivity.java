package com.mercury.demo.motionlayout;

import android.app.Activity;
import android.os.Bundle;

import com.mercury.demo.R;
import com.mercury.demo.viewpager.ListItemAdapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MotionTestActivity extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_layout);


    }

    // a - b
    // a oncreate onstart onresume onpuase b oncreate onstart onresume a onstop
    //b finish b onpuase a onrestart onstart onresume b onstop ondestroy


    /*// activity                fragemnt

    oncreteview
    onattachfragment



    oncreate
                                onattach
                                oncreate
                                oncreateView
                                onViewCreate
                                onActiviyCreate
                                onstart



    onstart



                                onresume
    onresume


                                onpuase
    onpuase


                                onstop

    onstop
                                ondestroyview
                                ondestory
                                ondettach



    ondestory


*/}
