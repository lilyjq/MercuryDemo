package com.mercury.demo.common;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RalewayTextView extends AppCompatTextView {

    public RalewayTextView(Context context) {
        super(context);
        init(context);
    }

    public RalewayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RalewayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private static final String tf = "fonts/SIMLI.TTF";
    private void init(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),tf);
        setTypeface(typeface,Typeface.NORMAL);
    }

}
