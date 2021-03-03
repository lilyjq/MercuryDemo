package com.mercury.demo.aty;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.mercury.demo.R;
import com.mercury.demo.util.DisplayUtil;

public class WaterMaskActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_watermask);
        addWater();
    }



    private Bitmap createWaterMask(Bitmap originBitmap, Bitmap waterBitmap){
        if(originBitmap == null){
            return null;
        }


        int w = originBitmap.getWidth();
        int h = originBitmap.getHeight();


        int ww = waterBitmap.getWidth();
        int wh = waterBitmap.getHeight();


        Bitmap newb = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newb);
        canvas.drawBitmap(originBitmap,0,0,null);
        canvas.drawBitmap(waterBitmap,w-ww+5,h-wh+5,null);
        return  newb;

    }



    private void addWater(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.math1);

        Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
        paint.setTextSize(18);
        paint.setTextAlign(Paint.Align.LEFT);
        float a = paint.measureText("我是水印！");
        Bitmap bitmap2 = Bitmap.createBitmap((int) a,50, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);

        canvas.drawText("我是水印！",0,20,paint);
        Bitmap water = createWaterMask(bitmap,bitmap2);
        ImageView iv = findViewById(R.id.iv);
        iv.setImageBitmap(water);

    }
}
