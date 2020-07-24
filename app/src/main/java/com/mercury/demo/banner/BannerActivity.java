package com.mercury.demo.banner;

import android.os.Bundle;
import android.util.Log;

import com.mercury.demo.R;
import com.mercury.demo.tranformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class BannerActivity extends AppCompatActivity {


    ViewPager2 viewPager2;
    BannerAdapter adapter;
    int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        viewPager2 = findViewById(R.id.viewpager2);
         adapter = new BannerAdapter();
        viewPager2.setAdapter(adapter);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BannerActivity.this.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
                if(state == ViewPager2.SCREEN_STATE_OFF){
                    if(position == 4){
                        viewPager2.setCurrentItem(1,false);
                    }else if(position == 0){
                        viewPager2.setCurrentItem(3,false);
                    }
                }
            }
        });
        List<String> id = new ArrayList<>();
        id.add("111");
        id.add("222");
        id.add("333");
        id.add("444");
        id.add("555");
        id.add("666");
        id.add("777");

        for (int i=0;i<id.size();i++){
            Log.e("test test",id.get(i));
        }
    }
}
