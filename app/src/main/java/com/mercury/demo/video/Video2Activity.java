package com.mercury.demo.video;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//2\MediaPlayer+SurfaceView+自定义控制器
public  class Video2Activity extends AppCompatActivity {


    /**
     * surfaceView 双缓冲，可以在独立线程运行，不会影响主线程，缺点不能transtion,rotation.scale等变换也不能放在其他viewgroup 中不能进行透明度换算
     * 不能嵌套使用  在wms中单独创建窗口
     *
     * textureview 可以用于是实现live preview 等功能  可以移动旋转缩放动画等效果 必须硬件加速 占用内存比surfaceView高  在5.0前主线程渲染，5.0后有的暗单独的渲染线程

     需要不断告诉更新画布的游戏来说，SurfaceView绝对是极好的选择。但是比如视频播放器或相机应用的开发，TextureView则更加适合。
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
