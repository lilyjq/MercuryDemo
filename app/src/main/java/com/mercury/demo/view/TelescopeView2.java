package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mercury.demo.R;

class TelescopeView2 extends View {

    private Bitmap bitmap;
    private ShapeDrawable shapeDrawable;
    //放大镜的半径
    private static final int RADUIS = 180;
    //放大镜的倍数
    private static final int FACTOR = 2;

    private final Matrix matrix = new Matrix();

    public TelescopeView2(Context context) {
        super(context);
        init();
    }

    public TelescopeView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TelescopeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TelescopeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        matrix.setTranslate(RADUIS-x*FACTOR,RADUIS-y*FACTOR);
        shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
        shapeDrawable.setBounds(x-RADUIS,y-RADUIS,x+RADUIS,y+RADUIS);
        invalidate();

        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmap == null){
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.fj1);
            bitmap = Bitmap.createScaledBitmap(bp,getWidth(),getHeight(),false);
            BitmapShader bitmapShader = new BitmapShader(
                    Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()*FACTOR,bitmap.getHeight()*FACTOR,true),
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            shapeDrawable = new ShapeDrawable(new OvalShape());
            shapeDrawable.getPaint().setShader(bitmapShader);
            shapeDrawable.setBounds(0,0,RADUIS*2,RADUIS*2);

        }


        canvas.drawBitmap(bitmap,0,0,null);
        shapeDrawable.draw(canvas);
    }
}
