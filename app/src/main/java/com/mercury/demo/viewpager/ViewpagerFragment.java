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

public class ViewpagerFragment extends Fragment {

    ViewPager viewPager;


//    ViewPager2 viewPager2;
    MyTabLayout myTabLayout;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test,container,false);
        myTabLayout = view.findViewById(R.id.my_tab);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        myTabLayout.bindViewPager(viewPager);

//        viewPager2 = view.findViewById(R.id.viewpager2);
//        viewPager2.setAdapter(new VP2Adapter(this));
//        myTabLayout.setUpWith(viewPager2);

        return view;

    }




}
