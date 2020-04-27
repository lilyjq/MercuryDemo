package com.mercury.demo.viewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.mercury.demo.R;
import com.mercury.demo.common.RalewayTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class MyTabLayout extends FrameLayout implements View.OnClickListener {

    private List<RalewayTextView> list;
    private ViewPager2 viewPager2;
    private HorizontalScrollView horizontalScrollView;



    public MyTabLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MyTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_test_tab,this);
        list = new ArrayList<>();
        list.add((RalewayTextView) findViewById(R.id.tag0));
        list.add((RalewayTextView) findViewById(R.id.tag1));
        list.add((RalewayTextView) findViewById(R.id.tag2));
        list.add((RalewayTextView) findViewById(R.id.tag3));
        list.add((RalewayTextView) findViewById(R.id.tag4));
        list.add((RalewayTextView) findViewById(R.id.tag5));
        for(RalewayTextView tv:list){
            tv.setOnClickListener(this);
        }
        horizontalScrollView = findViewById(R.id.horizontal_scroll);
    }


    @Override
    public void onClick(View v) {
         selectView(v,true);
    }


    RalewayTextView selectedView;
    private void selectView(View view ,boolean needchangeViewpager){
        selectedView = (RalewayTextView) view;
        for(RalewayTextView tv: list){
            if(tv == selectedView){
                tv.setTextColor(getContext().getResources().getColor(R.color.color_pink));
            }else{
                tv.setTextColor(getContext().getResources().getColor(R.color.textcolor));
            }
        }

        if(needchangeViewpager && viewPager2 != null){
            viewPager2.setCurrentItem(list.indexOf(selectedView),true);
        }else if(needchangeViewpager && viewPager != null){
            viewPager.setCurrentItem(list.indexOf(selectedView),true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int mLastScrollN;
    public void setUpWith(final ViewPager2 viewPager2){
        this.viewPager2 = viewPager2;
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int center = getWidth()/2;
                int offset = (int) (list.get(position).getWidth()*positionOffset);
                int scrollN = list.get(position).getLeft()+offset;

                int herf = 0;
                if(position<list.size()-1){
                    TextView tv= list.get(position+1);
                    herf = (int) (tv.getWidth()*positionOffset/2);
                }

                if(position >0 || offset>0){
                    scrollN=  scrollN-center;
                    scrollN = scrollN+herf;
                }

                if(scrollN != mLastScrollN){
                    mLastScrollN = scrollN;
                    horizontalScrollView.scrollTo(scrollN,0);
                }


            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                selectView(list.get(position),false);
            }
        });


//        viewPager2.getAdapter().get
    }



    private ViewPager viewPager;

    public void bindViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int center = getWidth()/2;
                int offset = (int) (list.get(position).getWidth()*positionOffset);
                int scrollN = list.get(position).getLeft()+offset;

                int herf = 0;
                if(position<list.size()-1){
                    TextView tv= list.get(position+1);
                    herf = (int) (tv.getWidth()*positionOffset/2);
                }
//
                if(position >0 || offset>0){
                    scrollN=  scrollN-center;
                    scrollN = scrollN+herf;
                }

                if(scrollN != mLastScrollN){
                    mLastScrollN = scrollN;
                    horizontalScrollView.scrollTo(scrollN,0);
                }
            }

            @Override
            public void onPageSelected(int position) {
                selectView(list.get(position),false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
