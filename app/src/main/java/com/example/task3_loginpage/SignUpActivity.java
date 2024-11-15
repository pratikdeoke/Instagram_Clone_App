package com.example.task3_loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    // Object Creation

   // private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
    EditText et_signup_name, et_signup_mobileNum, et_signup_emailid, et_signup_username, et_signup_password;
    Animation fadeIn;
    ImageView img_signup_logo;

    Button btn_signup_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("");

        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Finding ID
        et_signup_name = findViewById(R.id.et_signup_name);
        et_signup_mobileNum = findViewById(R.id.et_signup_mobileNum);
        et_signup_emailid = findViewById(R.id.et_signup_emailid);
        et_signup_username = findViewById(R.id.et_signup_username);
        et_signup_password = findViewById(R.id.et_signup_password);
        btn_signup_signup = findViewById(R.id.btn_signup_signup);
        img_signup_logo = findViewById(R.id.img_signup_logo);



        // Animation
        fadeIn = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.fadein);
        img_signup_logo.setAnimation(fadeIn);

        // Validations on SignUpActivity
        btn_signup_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_signup_name.getText().toString().isEmpty()) {
                    et_signup_name.setError("Please Enter Your Name");
                } else if (et_signup_mobileNum.getText().toString().isEmpty()) {
                    et_signup_mobileNum.setError("Please Enter Your Mobile Number ");
                } else if (et_signup_mobileNum.getText().toString().length() != 10) {
                    et_signup_mobileNum.setError("Length Must Be 10 Digits");
                } else if (et_signup_emailid.getText().toString().isEmpty()) {
                    et_signup_emailid.setError("Please Enter Your Email-ID");
                } else if (!et_signup_emailid.getText().toString().contains("@") || !et_signup_emailid.getText().toString().contains(".com")) {
                    et_signup_emailid.setError("Enter Valid Email-ID");
                } else if(et_signup_username.getText().toString().isEmpty()) {
                    et_signup_username.setError("Please Enter Your Username");
                } else if (!et_signup_username.getText().toString().matches("(.*[a-z]).*")) {
                    et_signup_username.setError("Username Must Contain At Least One Lowercase");
                } else if (!et_signup_username.getText().toString().matches("(.*[A-Z]).*")) {
                    et_signup_username.setError("Username Must Contain At Least One Uppercase");
                } else if (!et_signup_username.getText().toString().matches("(.*[0-9]).*")) {
                    et_signup_username.setError("Username Must Contain At Least One Digit");
                } else if (!et_signup_username.getText().toString().matches("(.*[@#$%^&+=]).*")) {
                    et_signup_username.setError("Username Must Contain At Least One Special Character");
                } else if (et_signup_password.getText().toString().isEmpty()) {
                    et_signup_password.setError("Please Enter Your Password");
                } else if (et_signup_password.getText().toString().length() < 8) {
                    et_signup_password.setError("Length Must Be Greater Than 8 characters");
                } else {
                    Toast.makeText(SignUpActivity.this, "Your Account Created Successfully", Toast.LENGTH_SHORT).show();
                }
            }

        });




//        else if (!PASSWORD_PATTERN.matcher(et_signup_username.getText().toString()).matches()) {
//        et_signup_username.setError("Username");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
//            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}