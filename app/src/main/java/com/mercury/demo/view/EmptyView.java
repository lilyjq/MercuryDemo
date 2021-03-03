package com.mercury.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

import com.mercury.demo.R;

class EmptyView extends View {

    public EmptyView(Context context) {
        super(context);
    }

    /**
     * 阴影
     * paint.setshadowlayout
     * paint.clearShadow
     *
     *
     *
     *
     * paint.setMaskFilter
     * BulrMaskFilter 、、发光效果 图片阴影
     *
     * bitmap.extractAlphna 新建一张和bitmap 一样的alpha值 空白图片
     *
     * setLayerType(LAYER_TYPE_SOFTWARE，null) 禁用硬件加速
     *
     * shader 着色器
     *
     * paint.setShader  从当前画布左上角开始绘制
     *    bitmapShader 类似于印章   可以用于展示部分图片
     *    LinearGradient 先行渐变
     *    RadialGradient 放射渐变
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
     *    1-> 禁用硬件加速
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
     */


    private void test(){


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj,options);



        Bitmap bitmap1 = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);//100*100的空白图片


    }


}
