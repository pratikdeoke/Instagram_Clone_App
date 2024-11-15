package com.example.task3_loginpage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {

    // Object Creation
    ConstraintLayout cl;
    ImageView img;
    Animation fadeIn;
    TextView tv_from, tv_meta;
    MotionLayout motionLayout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();



        // Finding ID
        tv_from = findViewById(R.id.txt_From);
        tv_meta = findViewById(R.id.txt_meta);
        img = findViewById(R.id.img_logo);

        // Animation
        fadeIn = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein);
        tv_from.setAnimation(fadeIn);
        tv_meta.setAnimation(fadeIn);
        img.setAnimation(fadeIn);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {

                        if (!task.isSuccessful()) {
                            //Toast.makeText(SplashActivity.this, "Fetching FCM registration token failed", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        editor.putString("mytoken",token).commit();

                        // Log and toast
                        Toast.makeText(SplashActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        // Intent to LoginActivity
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

        }, 5000);


    }
}