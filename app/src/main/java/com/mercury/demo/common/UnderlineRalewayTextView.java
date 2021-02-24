package com.mercury.demo.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.Nullable;

import com.mercury.demo.R;
import com.mercury.demo.util.DisplayUtil;

class UnderlineRalewayTextView extends RalewayTextView {


//    private int textSize;
    private Paint paint;


    public UnderlineRalewayTextView(Context context) {
        super(context);
        init();
    }

    public UnderlineRalewayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public UnderlineRalewayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.color_pink));
        paint.setTextSize(getTextSize());
        paint.setTypeface(getTypeface());
    }



    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        paint.setTextSize(size);

    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width  =  paint.measureText(getText().toString().trim());
        int count = getLayout().getLineCount();

        if(count == 1 ){
            if(getGravity() == Gravity.CENTER){
                int start = (int) ((getWidth()-width)/2);
                final LinearGradient linearGradient = new LinearGradient(getPaddingLeft()+start, getHeight() - 8, getPaddingLeft()+width+start, getHeight(),
                        Color.parseColor("#ffda9b"), Color.parseColor("#99cb86ba"), Shader.TileMode.CLAMP);
                paint.setShader(linearGradient);
                canvas.drawRect(getPaddingLeft()+start,getHeight()-8,getPaddingLeft()+width+start,getHeight(),paint);
            }else{
                canvas.drawRect(getPaddingLeft(), getHeight() - 8, getPaddingLeft() + width, getHeight(), paint);
            }
        }
//        canvas.get
//        canvas.drawRect();
    }
}
