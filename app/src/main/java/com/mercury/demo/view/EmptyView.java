package com.mercury.demo.view;

class EmptyView {

    /**
     * 阴影
     * paint.setshadowlayout
     * paint.clearShadow
     *
     *
     * 发光效果
     *
     * paint.setMaskFilter
     * BulrMaskFilter
     *
     * bitmap.extractAlphna 新建一张和bitmap 一样的alpha值 空白图片
     *
     * setLayerType(LAYER_TYPE_SOFTWARE，null) 禁用硬件加速
     *
     * shader 着色器
     *
     * paint.setShader
     *    bitmapShader 类似于印章   可以用于展示部分图片
     *    LinearGradient 先行渐变
     *    RadialGradient 放射渐变
     *
     *    图片绘制增大
     *    //TelescopeView
     *     Canvas canvas1 = new Canvas(mBitmapBg);
     *     canvas1.drawBitmap(mBitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);
     *
     * // Metrax
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
     */
}
