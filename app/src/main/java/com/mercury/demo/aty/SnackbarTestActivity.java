package com.mercury.demo.aty;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.mercury.demo.R;

import org.w3c.dom.Text;

/**
 * 1.android 11 禁止后台自定义taost
 * 2.texttoast 不允许自定义
 * 3.setview被弃用
 * 4.新增toast.callback回调
 * 官方建议使用snackbar
 */

public class SnackbarTestActivity extends Activity implements View.OnClickListener {



    TextView mSimpleTv;
    TextView mCustomTv;
    ConstraintLayout layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_snackbartest);
        mSimpleTv = findViewById(R.id.tv_s);
        mCustomTv = findViewById(R.id.tv_c);
        layout = findViewById(R.id.parent);

        mSimpleTv.setOnClickListener(this);
        mCustomTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tv_s){
            makeSimpleSnackbar();
        }
        if(id == R.id.tv_c){
            makeCustomSnackbar();
        }


    }

    private void makeSimpleSnackbar(){
      Snackbar snackbar =   Snackbar.make(layout,"Snackbar_Snackbar_Snackbar_Snackbar",Snackbar.LENGTH_LONG)

                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

      snackbar.getView().setBackgroundColor(Color.BLUE);
//      TextView tv = snackbar.getView().findViewById(R.id.snackbar_text);
//      tv.setTextColor(Color.RED);
      snackbar.setTextColor(Color.WHITE);
      snackbar.setActionTextColor(Color.YELLOW);
      snackbar.show();

    }

    private void makeCustomSnackbar(){

        View view = LayoutInflater.from(this).inflate(R.layout.layout_custon,null,false);
        Snackbar snackbar =   Snackbar.make(layout,"Snackbar_Snackbar_Snackbar_Snackbar",Snackbar.LENGTH_LONG)

                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        snackbar.getView().setBackgroundResource(R.drawable.bg_pink_23);

//      TextView tv = snackbar.getView().findViewById(R.id.snackbar_text);
//      tv.setTextColor(Color.RED);
//        Drawable drawable = getResources().getDrawable(R.drawable.math1);//图片自己选择
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        tv.setCompoundDrawables(drawable, null, null, null);
//          textView.setCompoundDrawablePadding(20);
        snackbar.setTextColor(Color.WHITE);
        snackbar.setActionTextColor(Color.YELLOW);

        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
//        layout.setPadding(50,50,50,100);
//        layout.getLayoutParams()

//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(snackbar.getView().getLayoutParams().width,snackbar.getView().getLayoutParams().height );
//        params.gravity = Gravity.BOTTOM;
//
//        params.setMargins(50,100,50,100);
//        params.gravity = Gravity.BOTTOM;
//        snackbar.getView().setLayoutParams(params);

        ViewGroup.LayoutParams params1 = layout.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) params1).setMargins(0,100,0,100);
        layout.setLayoutParams(params1);
//
        snackbar.show();
    }
}
