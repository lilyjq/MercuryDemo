package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.Keep;

import com.mercury.demo.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class EmptyView extends SurfaceView {

    public EmptyView(Context context) {
        super(context);
    }

    /**
     *
     * 绘制文字
     *
     * Paint.FontMetricsInt fm = paint.getFontMetricsInt()
     * fm.top = top-baseline
     * 阴影
     * paint.setshadowlayout
     * paint.clearShadow
     *
     *
     *
     *
     * paint.setMaskFilter
     * BulrMaskFilter 、、发光效果 图片阴影
     * new BlurMaskFilter(50,BlurMaskFilter.Blur.NORMAL)//边缘发光半径，发光类型
     * Blur.INNER 内 Blur.SOlID 外 NORMAL 内外发光 OUT 仅显示发光效果其他透明
     *
     * bitmap.extractAlphna 新建一张和bitmap 一样的alpha值 空白图片
     *
     * setLayerType(LAYER_TYPE_SOFTWARE，null) 禁用硬件加速 会导致view重绘 原因未知
     *
     * shader 着色器
     *
     * paint.setShader  从当前画布左上角开始绘制
     *    bitmapShader 类似于印章   可以用于展示部分图片
     *    LinearGradient 先行渐变
     *    RadialGradient 放射渐变
     *
     *  new BitmapSharder(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)//图片 x轴模式 y
     *
     *    TileMode.CLAMP
     *    TileMode.REPEAT
     *    TileMode.MIRROR
     *
     *    图片绘制增大
     *    //TelescopeView
     *     Canvas canvas1 = new Canvas(mBitmapBg);
     *     canvas1.drawBitmap(mBitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);
     *
     * // Matrix
     *
     *
     *    xfermode
     *    paint.setXfermode()
     *    1-> 禁用硬件加速 （部分代码不能使用硬件加速）
     *    2-> 使用离屏绘制
     *    //新建图层
     *    int layoutId = canvas.savelayer(0,0,getwidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
     *    //核心绘制代码
     *    。。。
     *    //还原图层
     *
     *
     *
     *    canva.restoreToCount(layoutId)
     *
     * 1   AvoidXFermode   替换颜色 融合两张图片
     * new AvoidXFerMode(Color.White,100,AvoidXFermode.Mode.TARGET)   AvoidXFermode.Mode.AVOID
     * 2 PorterDuffXfermode
     * Xfermode设置之前的图像叫目标图像（给谁应用xmode）之后为源图像(拿什么应用xmode)
     * 色彩变化：
     * Mode.ADD 饱和度相加 Mode.LIGHTEN DARKEN MULTIPLY(正片叠底) OVERLAY 叠加 SCREEN 滤色
     * new ProterDuffermode(PorterDuff.Mode.SRC_IN)
     * 源图像模式 SRC SRC_IN SRC_OUT SRC_OVER SRC_ATOP
     * SRC_IN 倒影效果
     * SRC_OUT 橡皮擦 当目标图像有图像时结果显示空白像素，如果目标图像没有像素时显示源图像
     *
     *canvas.save
     * canvas.savelayout 会创建一个新的图层
     * restore 或restoretocount 回到原图层
     *
     *
     * drawable shape便签对应的是GradientDrawable
     *
     *
     * RectShape OvalShape ArcShape RoundRectShape PathShape
     * ShapeDrawable  drawable = new ShapeDrawable(new RectShape())
     * drawable.setBounds(new Rect(0,0,100,100))// 当前控件的位置
     * drawable.getPaint().setColor(Color.Red)
     * drawable.draw(canvas)
     *
     *
     *
     * bitmap 一般使用ARGB_8888 如果对透明度有要求就ARGB_565  8位= 1字节
     * BitmapFactory.Options
     * 1.inJustDecodeBounds 获取图片信息 不分配内存 长宽 MIME 类型
     * options.outWidth options.outHeight
     * 2.inSampleSize 压缩图片 采样率 越大 图片大小越小 约模糊
     *
     * 缩放比例 = 屏幕分辩率/w文件夹所在的分辨率
     *
     *
     * imageview.setstateDrawable(Imageview v,Paint p)//主要作用用于向imageview添加背景
     *
     * onFinishInflata() 调用时机在xml解析出对应的控件实例的时候，这时候控件已经生成但还没有被使用，如果对控件进行一些基础设置是最佳时机
     *
     * Bitmap.extralAlpha()
     * .getAllocationByteCount >19
     * getByteCount 12-19
     * getRowByte <12
     *
     * .setDenisty Scale = inTargetDenisty/inDenisty 缩放
     * 。compress(CompressFormat format,int quility,OutputStream stream)
     * CompressFormat.PNG CompressFormat.JPEG,CompressFormat.WEBP（>14）
     * quility 1-100 0 最低像素压缩 无损的压缩忽略改值
     * stream 输出
     * 返回boolean 压缩是否成功
     *
     *1. 问题 缓存问题、如何加载巨大图片、LRU
     * SurfaceView
     * 使用双缓冲技术
     * 自带画布，支持在子线程更新画布内容
     *
     * 应用场景：
     * view 当页面需要被动更新时，比如首饰交互
     * surfaceview 页面主动更新 比如一个人一直跑 需要一直重绘状态 避免阻塞线程
     * 当页面绘制需要频繁刷新，或刷新是数据处理量比较大时 比如视频播放以及s摄像头 camera
     *
     *
     */

    private void init(){
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        surfaceHolder.addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        Canvas canvas = surfaceHolder.lockCanvas();


        //TODO 绘图操作
        surfaceHolder.unlockCanvasAndPost(canvas);
        //当画布被其它线程锁定的时候 或者缓存canvas没有被创建的时候，surfaceHolder.lockCanvas会返回null
        //这样一来如果多个线程同时操作那么不仅要对画布做判空还要再画布为null的时候添加重试策略。

    }
    private void test(){


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj,options);



        Bitmap bitmap1 = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);//100*100的空白图片



        //手势检测
        //1 创建实例 OnGestureListener
        //2 创建 GestureDetector
        //3 在ontouch 中进行拦截

        TextView tv = null;
        GestureDetector detector = new GestureDetector(getContext(),new MyGesture());
        tv.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });

        GestureDetector.OnDoubleTapListener listener; //implment
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener; //extends 简化版 用哪个函数就重写哪个函数
        //onFiling

    }




}
