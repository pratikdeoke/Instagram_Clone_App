package com.example.task3_loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;

public class Story2Activity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story2);

        videoView = findViewById(R.id.vv_story2_video);

        String videoPath = "android.resource://"+getPackageName()+"/raw/rang";
        videoView.setVideoPath(videoPath);
        videoView.start();
    }
}