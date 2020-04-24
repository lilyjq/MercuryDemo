package com.mercury.demo.viewpager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pViewPagerAdapter extends FragmentPagerAdapter {

    List<TestVPFragment> list = new ArrayList<>();

    public pViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
        list.add(new TestVPFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "233333";
    }
}
