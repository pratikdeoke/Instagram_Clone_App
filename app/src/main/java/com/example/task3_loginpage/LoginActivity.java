package com.example.task3_loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {

    // Object Creation
    View view;

    EditText et_login_username, et_login_password;
    CheckBox cb_login_show_hide;
    Animation zoomIn;
    Button btn_login_login;
    TextView tv_login_title, tv_login_forgot, tv_login_loginfb, tv_signup;
    ImageView img_login_facebook_logo;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean doubleTap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (preferences.getBoolean("isLogin", false)) {

            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }


        // Finding ID
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        cb_login_show_hide = findViewById(R.id.cb_login_show_hide);
        btn_login_login = findViewById(R.id.btn_login_login);
        tv_login_title = findViewById(R.id.tv_login_title);
        tv_signup = findViewById(R.id.tv_login_signup);
        tv_login_loginfb = findViewById(R.id.tv_login_loginfb);
        img_login_facebook_logo = findViewById(R.id.img_login_facebook_logo);
        tv_login_forgot = findViewById(R.id.tv_login_forgot);

        // Checkbox
        cb_login_show_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        // Validations on LoginActivity
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_login_username.getText().toString().isEmpty()) {
                    et_login_username.setError("Please Enter Your Username");
                } else if (et_login_username.getText().toString().length() < 8) {
                    et_login_username.setError("Length Must Be Greater Than 8 Characters");
                } else if (et_login_password.getText().toString().isEmpty()) {
                    et_login_password.setError("Please Enter Your Password");
                }  else if (et_login_password.getText().toString().length() < 8) {
                    et_login_password.setError("Length Must Be Greater Than 8 Characters");
                } else {
                    Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.putExtra("username", et_login_username.getText().toString());
                    i.putExtra("password", et_login_password.getText().toString());
                    editor.putBoolean("isLogin", true).commit();
                    startActivity(i);
                    finish();
                }
            }
        });



        // Animation
        zoomIn = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.zoomin);
        tv_login_title.setAnimation(zoomIn);


        // Intent to SignUpActivity
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        // Intent to FacebookLoginActivity by TextView
        tv_login_loginfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, FacebookLoginActivity.class);
                startActivity(i);
            }
        });

        // Intent to FacebookLoginActivity by ImageView
        img_login_facebook_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, FacebookLoginActivity.class);
                startActivity(i);
            }
        });


        // Forgot Password Text
        tv_login_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://help.instagram.com/374546259294234#:~:text=Tap%20Get%20help%20logging%20in%20on%20the%20login%20screen.&text=Enter%20your%20Instagram%20username%2C%20email,phone%20number%2C%20then%20tap%20Next.&text=Tap%20Forgot%20password%20on%20the%20login%20screen.&text=Enter%20your%20Instagram%20username%20or,then%20tap%20Send%20login%20link."));
                startActivity(i);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (doubleTap) {
            super.onBackPressed();
        } else {
            Toast.makeText(LoginActivity.this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
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