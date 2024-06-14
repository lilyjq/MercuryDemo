package com.mercury.demo.scrollcut;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mercury.demo.R;

import com.mercury.demo.util.DisplayUtil;
import com.mercury.demo.view.AnimationValueView;
import com.mercury.demo.view.Mytestview;
import java.util.logging.Logger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ScrollCutAcitivty extends AppCompatActivity {

    ScrollLineView scrollLineView;
    ScrollCutView scrollCutView;


    ScrollLineView02 scrollLineView2;
    ImageView imageView;
    private int ivWidth,ivHeight;


    RecyclerView recyclerView;

    LyricView lyricView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_cut);
        scrollCutView = findViewById(R.id.cutview);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dog);
        scrollCutView.setImageBitmap(bitmap);
        scrollLineView= findViewById(R.id.line_view);
        scrollLineView.bindScrollCutView(scrollCutView);


        scrollLineView2 = findViewById(R.id.scrollline);
        imageView = findViewById(R.id.imag);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                ivWidth = imageView.getWidth();
                ivHeight = imageView.getHeight();

            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //暂时没有解决因为重绘导致scrollline回原点的办法
        scrollLineView2.setChangeListener(new ScrollLineView02.ChangeListener() {
            @Override
            public void changLeft(final int left) {
//                if(ivHeight>0 &&ivWidth>0) {
                    Log.e("left left",left+"");
//                    scrollLineView2.stopOnMeasure(true);
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                            params.width = left;
                            imageView.setLayoutParams(params);

//                }
            }
        });
        //MultiSlider


        get();

        lyricView = findViewById(R.id.lyr);

        lyricView.postDelayed(new Runnable() {
            @Override public void run() {
                lyricView.loadLrc();
            }
        }, 3000);
    }

    AnimationValueView animationValueView ;
    HorizontalScrollView scrollView;
    private void get(){
        scrollView = findViewById(R.id.scroll_view);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);/*{
            @Override public boolean canScrollVertically() {
                return super.canScrollVertically();
            }

            @Override public boolean canScrollHorizontally() {
                return true;
            }
        };*/
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter());

        animationValueView = findViewById(R.id.valueview);
        animationValueView.setCallback(new AnimationValueView.onScrollCallback() {
            @Override public void onScroll(int x) {
                scrollView.smoothScrollBy(x,0);
            }
        });



    LinearLayout layout = findViewById(R.id.ll);
    for(int i=0;i<100;i++){
        ImageView imageView1 = new ImageView(this);
        imageView1.setBackgroundColor(Color.RED);
        LinearLayout.LayoutParams layoutParams =
            new LinearLayout.LayoutParams((int) DisplayUtil.dp2px(40), (int) DisplayUtil.dp2px(40));
        layoutParams.rightMargin = (int) DisplayUtil.dp2px(20);
        layout.addView(imageView1, layoutParams);
    }

    animationValueView.setHopeWidth((int) (100*DisplayUtil.dp2px(40)));



    }




    public void onClick(View view) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        params.width = params.width+10;
        imageView.setLayoutParams(params);
    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.aaa,parent,false)) ;
        }

        @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(holder.textView != null) {
                holder.textView.setText(String.valueOf(position));
            }
            getMeasureHeight(holder.itemView);

        }

        @Override public int getItemCount() {
            return 100;
        }


        void   getMeasureHeight(View itemView){
            int childWidthSpecw = View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.AT_MOST);
            int childWidthSpech = View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.AT_MOST);
            //调用measure方法之后就可以获取宽高
            itemView.measure(childWidthSpecw, childWidthSpech);
            //int bh = View.MeasureSpec.getSize(height);
            Log.i("eeee", "getMeasureHeight: bh :"+itemView.getMeasuredHeight());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        Mytestview textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);


        }



    }
}
