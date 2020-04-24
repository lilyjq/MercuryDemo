package com.mercury.demo.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercury.demo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.VpViewholder> {

    private Context context;

    public VPAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VpViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_vp,parent,false);
        return new VpViewholder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull VpViewholder holder, int position) {
          holder.tv.setText("postion:"+position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class VpViewholder extends RecyclerView.ViewHolder{

        TextView tv;
        public VpViewholder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
