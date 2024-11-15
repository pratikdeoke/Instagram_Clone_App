package com.example.task3_loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ReelsActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private List<Video> videoList;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reels);

        videoList = new ArrayList<>();
        viewPager2 = findViewById(R.id.vp_reels_viewpager2);

        videoList.add(new Video("android.resource://"+getPackageName()+"/"+R.raw.mystory, "@funnyVideos", "Follow for more such videos.", "63.7k", "122", "12.6k"));
        videoList.add(new Video("android.resource://"+getPackageName()+"/"+R.raw.rang, "feel_the_music", "Maine toh dheere se", "74.8k", "274", "31.2k"));
        videoList.add(new Video("android.resource://"+getPackageName()+"/"+R.raw.rohit, "hitman_cha_fan", "Rohit Sharma", "1.2M", "529", "205k"));
        videoList.add(new Video("android.resource://"+getPackageName()+"/"+R.raw.virat, "vk_status", "The RunMachine - Virat Kohli", "965k", "410", "110k"));
        videoList.add(new Video("android.resource://"+getPackageName()+"/"+R.raw.ronaldo, "cr7_lover", "Cristiano", "102k", "368", "61.5k"));

        adapter = new VideoAdapter(videoList);
        viewPager2.setAdapter(adapter);
    }
}