package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.mercury.demo.R;

public class NoticeViewFlipper extends ViewFlipper {

    int[] str = {R.string.test_transformPivot, R.string.text_test, R.string.tab4, R.string.tab3, R.string.tab1,R.string.tab6};

//transformPivotX transformPivotY,君不见黄河之水天上来，奔流到海不复回。,//朝如青丝暮成雪,transformPivotX,君不见黄河

    public NoticeViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        textflipper2();
    }


    TextView textView;
    TextView textView1;
    TextView textView2,textView3;
    private boolean showFirst = true;

    private void textflipper2() {
        setInAnimation(getContext(), R.anim.flip_in);
        setOutAnimation(getContext(), R.anim.flip_out);

        textView = new TextView(getContext());
        textView.setTextSize(16);
        textView.setText(getResources().getString(str[0]));
        textView.setPadding(100, 20, 10, 20);
        addView(textView);



        textView1 = new TextView(getContext());
        textView1.setTextSize(16);
        textView1.setTextColor(Color.BLUE);
        textView1.setText(getResources().getString(str[1]));
        textView1.setPadding(100, 20, 10, 20);
        addView(textView1);


   /*     textView2 = new TextView(getContext());
        textView2.setTextSize(16);
        textView2.setTextColor(Color.WHITE);
        textView2.setText(getResources().getString(str[2]));
        textView2.setPadding(100, 20, 10, 20);
        addView(textView2);



        textView3 = new TextView(getContext());
        textView3.setTextSize(16);
        textView3.setTextColor(Color.YELLOW);
        textView3.setText(getResources().getString(str[3]));
        textView3.setPadding(100, 20, 10, 20);
        addView(textView3);*/
        startFlipping();
    }

   int nowpos = 1;
    @Override
    public void showNext() {
        super.showNext();
        nowpos = nowpos+1;
        if(nowpos == str.length){
            nowpos = 0;
        }

        int pos= nowpos+2;
       if(pos >= str.length){
           pos = pos- str.length;
       }

/*
        int realPos = nowpos%4;
        Log.e("pos ",pos+"             realpos  "+realPos+ "nowpos"+nowpos);

        if(realPos == 0){
            textView2.setText(getContext().getResources().getString(str[pos]));
        }
        if(realPos == 1){
            textView3.setText(getContext().getResources().getString(str[pos]));
        }

        if(realPos == 2){
            textView.setText(getContext().getResources().getString(str[pos]));
        }
        if(realPos == 3){
            textView1.setText(getContext().getResources().getString(str[pos]));
        }*/





        if(!showFirst){
            textView1.setText(getContext().getResources().getString(str[nowpos]));
        }else{
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText(getContext().getResources().getString(str[nowpos]));
                }
            },3000);
        }
        showFirst = !showFirst;

    }



}
