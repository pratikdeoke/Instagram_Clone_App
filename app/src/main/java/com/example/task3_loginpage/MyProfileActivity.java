package com.example.task3_loginpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {


    String str_username, str_password, str_token;
    TextView tv_username, tv_password, tv_my_token;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        setTitle("My Profile");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        tv_username = findViewById(R.id.tv_my_profile_username);
        tv_password = findViewById(R.id.tv_my_profile_password);
        tv_my_token = findViewById(R.id.tv_my_profile_my_token);

       // str_token = preferences.getString("mytoken", "");
        tv_my_token.setText(preferences.getString("mytoken", ""));


        Intent i = getIntent();
        str_username = i.getStringExtra("username");
        str_password = i.getStringExtra("password");

        tv_username.setText(str_username);
        tv_password.setText(str_password);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);

        actionBar.setBackgroundDrawable(colorDrawable);
    }
}