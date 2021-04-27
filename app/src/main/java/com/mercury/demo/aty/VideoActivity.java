package com.mercury.demo.aty;

import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mercury.demo.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class VideoActivity extends AppCompatActivity {


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public  @interface BindId{
        int value();
    }


    @BindId(value = R.id.video_view)
    VideoView videoView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.video_view);
        String path ="http://resource.usdget.com/edaf9-2021-01-22-11/repository/10/video/iLod0tTT.mp4";
        videoView.setVideoPath(path);
        videoView.start();
    }
}
