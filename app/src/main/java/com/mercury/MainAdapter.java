package com.mercury;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercury.demo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private List<String> list;
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        addData();
    }

    private void addData(){
        list.add(context.getString(R.string.avatar_camera));
        list.add(context.getString(R.string.layout_test));
        list.add(context.getString(R.string.radar_view));
        list.add(context.getString(R.string.indicator));
        list.add(context.getString(R.string.progressview));
        list.add(context.getString(R.string.behavior));
        list.add(context.getString(R.string.path));
        list.add(context.getString(R.string.scrollcut));
        list.add(context.getString(R.string.banner));
        list.add(context.getString(R.string.motion));
        list.add(context.getString(R.string.transition));
        list.add(context.getString(R.string.watermask));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenr != null){
                    listenr.onItemClick(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
        }
    }


    private onItemClickListenr listenr;
    public void setListenr(onItemClickListenr listenr){
        this.listenr = listenr;
    }


    public  interface  onItemClickListenr{
        void onItemClick(int pos);
    }
}
