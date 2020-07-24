package com.mercury.demo.tranformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {


    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;


    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        if (position < -1) {  //[-Infinity,-1]
            //this page is way off -screen to the left
            page.setAlpha(0);
        } else if (position <= 1) {//[-1.1]
            //Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMarign = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {//[-1,0]
//                page.setTranslationX(horzMarign - vertMargin / 2);
//            } else {
//                page.setTranslationX(-horzMarign + vertMargin / 2);
            }
            //Scale the page down(between MIN_SCALE and 1)
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            //fade the page relative to its size
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));


        } else {
            page.setAlpha(0);
        }
    }

}
