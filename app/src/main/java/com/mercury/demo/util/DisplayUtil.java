package com.mercury.demo.util;

import android.content.Context;

public class DisplayUtil {

    public static float sDensity = 1.0f;
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context,float spValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * spValue);
    }
}



