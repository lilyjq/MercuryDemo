package com.mercury.demo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

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
    public static float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}



