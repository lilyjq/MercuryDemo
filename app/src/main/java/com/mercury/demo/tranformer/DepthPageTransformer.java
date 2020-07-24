package com.mercury.demo.tranformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class DepthPageTransformer implements ViewPager2.PageTransformer {
    private static  final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        if(position <-1){//[-infinity,-1] 表示左边的view而且已经看不见了
            page.setAlpha(0);
        }else  if(position<=0){//[-1,0] 左边的view 且可以看见
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        }else if(position<=1){//(0,1]右边的view 且可以看见
            page.setAlpha(1-position);
            page.setTranslationX(pageWidth*-position);
            float scaleFactor = MIN_SCALE+(1-MIN_SCALE)*(1-Math.abs(position));
            page.setScaleY(scaleFactor);
            page.setScaleX(scaleFactor);
        }else{//(1,+infinity]右边的view 且看不见
            page.setAlpha(0);

        }

    }

//    -1   0   1

//    a 是第一页
//    b 是第二页
//    当前页为 a, 当  a  向左滑动,  直到滑到 b 时:
//    a 的position变化是  [-1 ,   0]   由  0  慢慢变到 -1
//    b 的position变化是  ( 0 ,   1]   由  1  慢慢变到  0
//
//    当前页为b,  当 b 向右滑动, 直到滑到a 时:
//    a 的position变化是  [-1 ,   0]   由  -1  慢慢变到 0
//    b 的position变化是  ( 0 ,   1]   由   0   慢慢变到 1
//            ————————————————
//    版权声明：本文为CSDN博主「crianzy」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/chen930724/java/article/details/50466199
}
