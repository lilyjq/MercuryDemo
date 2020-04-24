package com.mercury.demo.viewpager;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercury.demo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VP2Adapter extends FragmentStateAdapter {



    List<TestVPFragment> list = new ArrayList<>();




    public VP2Adapter(@NonNull Fragment fragment) {
        super(fragment);
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
