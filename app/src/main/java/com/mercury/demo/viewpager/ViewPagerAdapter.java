package com.mercury.demo.viewpager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<RefreshListFragment> list = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());

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
