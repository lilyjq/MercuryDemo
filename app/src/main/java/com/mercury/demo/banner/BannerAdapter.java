package com.mercury.demo.banner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercury.demo.R;
import com.youth.banner.Banner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private int[]color = new int[]{R.color.color_pink,R.color.color_yellow_line,R.color.textColorSecond};

    public BannerAdapter() {
//        Banner
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner,parent,false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        holder.cl.setBackgroundResource(color[getRealPos(position)]);

    }
   // 3 1 2 3 1
   // 0 1 2 3 4
   // 1 2 3
    private int getRealPos(int pos){
        int realPos;
        if(pos == 0){
            realPos = color.length-1;
        }else if(pos == color.length+1){
            realPos = 0;
        }else{
            realPos = pos-1;
        }
        return realPos;
    }
    @Override
    public int getItemCount() {
        return color.length+2;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout cl;
        BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            cl = itemView.findViewById(R.id.cl);
        }
    }
}
