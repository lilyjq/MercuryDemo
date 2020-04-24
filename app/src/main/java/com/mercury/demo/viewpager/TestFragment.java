package com.mercury.demo.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

//import com.google.android.material.tabs.TabLayout;
import com.mercury.demo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class TestFragment extends Fragment {

//    TabLayout tableLayout;
//    ViewPager viewPager;


    ViewPager2 viewPager2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test,container,false);
//        tableLayout = view.findViewById(R.id.tab);
//        tableLayout.setupWithViewPager(viewPager);
//        viewPager = view.findViewById(R.id.viewpager);
//        viewPager.setAdapter(new pViewPagerAdapter(getChildFragmentManager()));
//        tableLayout.setupWithViewPager(viewPager);
        viewPager2 = view.findViewById(R.id.viewpager2);
        viewPager2.setAdapter(new VP2Adapter(this));
//        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
//        viewPager2.
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
        return view;

    }




}
