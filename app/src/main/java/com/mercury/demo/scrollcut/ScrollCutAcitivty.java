package com.mercury.demo.scrollcut;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mercury.demo.R;

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
        //暂时没有解决因为重绘导致scrollline回原点的办法
        scrollLineView2.setChangeListener(new ScrollLineView02.ChangeListener() {
            @Override
            public void changLeft(final int left) {
//                if(ivHeight>0 &&ivWidth>0) {
                    Log.e("left left",left+"");
//                    scrollLineView2.stopOnMeasure(true);
//                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
//                            params.width = left;
//                            imageView.setLayoutParams(params);

//                }
            }
        });
    }


    public void onClick(View view) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        params.width = params.width+10;
        imageView.setLayoutParams(params);
    }
}
