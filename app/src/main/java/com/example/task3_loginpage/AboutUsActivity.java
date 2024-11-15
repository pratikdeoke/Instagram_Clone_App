package com.example.task3_loginpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class AboutUsActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        videoView = findViewById(R.id.vv_about_us_video);

        String videoPath = "android.resource://"+getPackageName()+"/raw/gpa";
        videoView.setVideoPath(videoPath);
        videoView.start();

        MediaController mediaController = new MediaController(AboutUsActivity.this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        setTitle("About Us");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);

        actionBar.setBackgroundDrawable(colorDrawable);
    }
}