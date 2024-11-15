package com.example.task3_loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity {

    boolean doubleTap = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    String str_username, str_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        str_username = intent.getStringExtra("username");
        str_password = intent.getStringExtra("password");
       // Toast.makeText(HomeActivity.this, str_username+" "+str_password, Toast.LENGTH_SHORT).show();


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        boolean isFirstTime = preferences.getBoolean("first", true);
        if (isFirstTime) {
            welcome();
        }

        tabLayout = findViewById(R.id.home_tablayout);
        viewPager = findViewById(R.id.home_viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(), "Home");
        //viewPagerAdapter.addFragment(new ReelsFragment(), "Reels");
        viewPagerAdapter.addFragment(new SearchFragment(), "Search");
        viewPagerAdapter.addFragment(new ChatsFragment(), "Chats");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setTitle("Home");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);

        actionBar.setBackgroundDrawable(colorDrawable);
    }

    private void welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Instagram");
        ad.setMessage("Welcome To Our App..!");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();

        editor = preferences.edit();
        editor.putBoolean("first", false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_menu_my_location) {
            Intent i = new Intent(HomeActivity.this, MyLocationActivity.class);
            startActivity(i);
        }

        if (item.getItemId() == R.id.home_menu_my_profile) {
            Intent i = new Intent(HomeActivity.this, MyProfileActivity.class);
            i.putExtra("username", str_username);
            i.putExtra("password", str_password);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_music) {
            Intent i = new Intent(HomeActivity.this, MusicPlayerActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_video) {
            Intent i = new Intent(HomeActivity.this, ReelsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_bluetooth) {
            Intent i = new Intent(HomeActivity.this, BluetoothActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_text_to_speech) {
            Intent i = new Intent(HomeActivity.this, TextToSpeechActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_settings) {
            Intent i = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_contact_us) {
            Intent i = new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_about_us) {
            Intent i = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.home_menu_logout) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Logout");
            ad.setMessage("Are You Sure Want To Exit..?");

            ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    editor.putBoolean("isLogin", false).commit();
                    startActivity(i);
                }
            }).create().show();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleTap) {
            super.onBackPressed();
        } else {
            Toast.makeText(HomeActivity.this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap = false;
                }
            }, 2000);
        }
    }
}