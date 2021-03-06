package com.mercury.demo.viewpager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VP2Adapter extends FragmentStateAdapter {



    List<RefreshListFragment> list = new ArrayList<>();




    public VP2Adapter(@NonNull Fragment fragment) {
        super(fragment);
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());
        list.add(new RefreshListFragment());

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
