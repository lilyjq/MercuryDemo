package com.mercury.demo.video;

import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mercury.demo.R;
import com.mercury.demo.util.TimeUtils;

import java.io.IOException;

import io.reactivex.exceptions.OnErrorNotImplementedException;

//2\MediaPlayer+SurfaceView+自定义控制器
public class Video2Activity extends AppCompatActivity implements View.OnClickListener {


    /**
     * surfaceView 双缓冲，可以在独立线程运行，不会影响主线程，缺点不能transtion,rotation.scale等变换也不能放在其他viewgroup 中不能进行透明度换算
     * 不能嵌套使用  在wms中单独创建窗口
     * <p>
     * textureview 可以用于是实现live preview 等功能  可以移动旋转缩放动画等效果 必须硬件加速 占用内存比surfaceView高  在5.0前主线程渲染，5.0后有的暗单独的渲染线程
     * <p>
     * 需要不断告诉更新画布的游戏来说，SurfaceView绝对是极好的选择。但是比如视频播放器或相机应用的开发，TextureView则更加适合。
     * <p>
     * 以下类用于在 Android 框架中播放声音和视频：
     * <p>
     * MediaPlayer  释放内存这是必要的,因为MediaPlayer底层是运行C++的函数方法.不要使用后,必需释放内存
     * 此类是用于播放声音和视频的主要 API。
     * AudioManager
     * 此类用于管理设备上的音频源和音频输出。
     * <p>
     * 创建MediaPlayer对象，并让它加载指定的视频文件。可以是应用的资源文件、本地文件路径、或者URL。
     * 在界面布局文件中定义SurfaceView组件，并为SurfaceView的SurfaceHolder添加Callback监听器。
     * 调用MediaPlayer对象的setDisplay(SurfaceHolder sh)将所播放的视频图像输出到指定的SurfaceView组件。
     * 调用MediaPlayer对象的prepareAsync()或prepare()方法装载流媒体文件
     * 调用MediaPlayer对象的start()、stop()和pause()方法来控制视频的播放。
     */


    SurfaceView surfaceView;
    TextView tv_play;
    TextView tv_back;
    TextView tv_forward;
    SeekBar seekBar;

    TextView startTime;
    TextView endTime;
    MediaPlayer mediaPlayer;


    Uri mUri;
    TextView mSlience;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        initView();
        surfaceView = findViewById(R.id.surface);
        mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.trailer);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer.setDisplay(holder);
                mediaPlayer.prepareAsync();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


        inipaly();
//        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.subscribe_all);

    }

    private void inipaly() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                startTime.setText(TimeUtils.longToStr(mp.getCurrentPosition()));
                endTime.setText(TimeUtils.longToStr(mp.getDuration()));
                seekBar.setMax(mp.getDuration());
                seekBar.setProgress(mp.getCurrentPosition());
            }
        });
        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {

            }
        });

        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

            }
        });

        try {
            mediaPlayer.setDataSource(this, mUri);
            mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);//缩放模式
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setVolume(0,0);

    }


    private void initView() {

        tv_play = findViewById(R.id.play);
        tv_back = findViewById(R.id.back);
        tv_forward = findViewById(R.id.forward);
        seekBar = findViewById(R.id.seekbar);
        startTime = findViewById(R.id.starttime);
        endTime = findViewById(R.id.endtime);
        mSlience = findViewById(R.id.silence);

        tv_play.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        tv_forward.setOnClickListener(this);
        mSlience.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.play) {
            play();
        }


        if (id == R.id.back) {
            backward();
        }


        if (id == R.id.forward) {
            forward();
        }
        if(id ==R.id.silence){
            silence();
        }
    }

    private boolean isSlience = true;
    private void silence(){
        if(isSlience){
            mediaPlayer.setVolume(0.6f,0.6f);
        }else{
            mediaPlayer.setVolume(0,0);
        }
        isSlience = !isSlience;

    }


    private void backward() {
        if (mediaPlayer != null) {
            int position = mediaPlayer.getCurrentPosition();
            if (position > 10000) {
                position -= 10000;
            } else {
                position = 0;
            }
            mediaPlayer.seekTo(position);
        }
    }

    private void play() {
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();

        } else {
            mediaPlayer.start();
        }

    }

    private void forward() {
        if (mediaPlayer != null) {
            int position = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(position + 10000);
        }
    }

    private void updateTime() {
        startTime.setText(TimeUtils.longToStr(mediaPlayer.getCurrentPosition()));
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
    }
}
