package com.example.task3_loginpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HomeFragment extends Fragment {

    ImageView img_your_story, img_apurva, img_shubham, img_sahil, img_reema;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_home, container, false);

        img_your_story = view.findViewById(R.id.img_home_your_story);
        img_apurva = view.findViewById(R.id.img_home_apurva);
        img_shubham = view.findViewById(R.id.img_home_shubham);
        img_sahil = view.findViewById(R.id.img_home_sahil);
        img_reema = view.findViewById(R.id.img_home_reema);

        img_your_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Story1Activity.class);
                startActivity(intent);
            }
        });

        img_apurva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Story2Activity.class);
                startActivity(intent);
            }
        });

        img_shubham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Story3Activity.class);
                startActivity(intent);
            }
        });

        img_sahil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Story4Activity.class);
                startActivity(intent);
            }
        });

        img_reema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Story5Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}