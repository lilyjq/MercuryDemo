package com.mercury.demo.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mercury.demo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ColorFragment extends Fragment {


    LinearLayout container;


    public static ColorFragment newInstance(int color){
        ColorFragment fragment = new ColorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("color",color);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color,container,false);
        container = view.findViewById(R.id.container);
        Bundle bundle = getArguments();
        int color= R.color.color_yellow;
        if(bundle != null)
         color = bundle.getInt("color");
        container.setBackgroundColor(getResources().getColor(R.color.color_yellow));
        return view ;
    }


}
