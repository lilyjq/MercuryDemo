package com.mercury.demo.video;

import android.Manifest;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mercury.demo.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.logging.Logger;

import pub.devrel.easypermissions.EasyPermissions;

import static android.media.AudioManager.ADJUST_MUTE;
import static android.media.AudioManager.ADJUST_TOGGLE_MUTE;
import static android.media.AudioManager.ADJUST_UNMUTE;
import static android.media.AudioManager.GET_DEVICES_OUTPUTS;
import static android.media.AudioManager.STREAM_MUSIC;

public class VideoActivity extends AppCompatActivity {


    /**
     * 1\MediaController+VideoView实现方式
     * 2\MediaPlayer+SurfaceView+自定义控制器
     * 3\MediaPlayer+SurfaceView+MediaController
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public  @interface BindId{
        int value();
    }


    @BindId(value = R.id.video_view)
    VideoView videoView;
    ImageView audoIv;
    private int state = 0 ;
    AudioManager audioManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.video_view);
         initAudio();
        audoIv = findViewById(R.id.audo);
        audoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 0){
                    state = 1;
                    audoIv.setImageResource(R.drawable.star1);
                    int va = audioManager.getStreamVolume(STREAM_MUSIC);
                    audioManager.adjustVolume(ADJUST_MUTE, 0);
                }else{
                    state = 0;
                    audoIv.setImageResource(R.drawable.star2);
                    //   FLAG_PLAY_SOUND 调整音量时播放声音
                    //                FLAG_SHOW_UI 调整时显示音量条,就是按音量键出现的那个
                    //                0表示什么也没有
                    audioManager.adjustVolume(ADJUST_UNMUTE, 0);
                }
            }
        });
        requestPermission();
    }
    int maxVolume;
    private void initAudio(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Log.e("maxVolume",maxVolume+"");

    }

    private void requestPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.INTERNET)) {
            playNet();
        } else {
            EasyPermissions.requestPermissions(this, "askquestion", 22, Manifest.permission.INTERNET);
        }

    }

    void playNet(){
        /**
         * 会有一个 <<  || >> 的播放控制
         */
        MediaController  controller = new MediaController(this);
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
        /**
         * 网络上的有些视频是有访问限制的
         *
         */
        String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//        String path ="https://media.w3.org/2010/05/sintel/trailer.mp4";
//        videoView.setVideoURI(Uri.parse(path));
        videoView.setVideoPath(path);
        videoView.start();


//        controller.show();
    }
}
