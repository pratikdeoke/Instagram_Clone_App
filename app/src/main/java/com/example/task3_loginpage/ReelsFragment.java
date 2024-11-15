package com.example.task3_loginpage;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ReelsFragment extends Fragment {

    private ViewPager2 viewPager2;
    private List<Video> videoList;
    private VideoAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_reels, container, false);

        videoList = new ArrayList<>();
        viewPager2 = view.findViewById(R.id.vp_fragment_reels_viewpager2);

        videoList.add(new Video("android.resource://"+getActivity().getPackageName()+"/"+R.raw.mystory, "My Reel", "Here's my first reel", "63.7k", "529", "2.6k"));
        videoList.add(new Video("android.resource://"+getActivity().getPackageName()+"/"+R.raw.rang, "My Reel", "Here's my first reel", "63.7k", "529", "2.6k"));
        videoList.add(new Video("android.resource://"+getActivity().getPackageName()+"/"+R.raw.rohit, "My Reel", "Here's my first reel", "63.7k", "529", "2.6k"));
        videoList.add(new Video("android.resource://"+getActivity().getPackageName()+"/"+R.raw.virat, "My Reel", "Here's my first reel", "63.7k", "529", "2.6k"));
        videoList.add(new Video("android.resource://"+getActivity().getPackageName()+"/"+R.raw.bhagwadhari, "My Reel", "Here's my first reel", "63.7k", "529", "2.6k"));

        adapter = new VideoAdapter(videoList);
        viewPager2.setAdapter(adapter);



        return view;

    }
}