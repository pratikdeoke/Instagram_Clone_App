package com.example.task3_loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacebookLoginActivity extends AppCompatActivity {

    // Object Creation
    EditText et_fblogin_username, et_fblogin_password;
    Button btn_fblogin_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);


        // Finding ID
        et_fblogin_username = findViewById(R.id.et_fblogin_username);
        et_fblogin_password = findViewById(R.id.et_fblogin_password);
        btn_fblogin_login = findViewById(R.id.btn_fblogin_login);


        // Validations on FacebookLoginActivity
        btn_fblogin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_fblogin_username.getText().toString().isEmpty()) {
                    et_fblogin_username.setError("Please Enter Phone Number Or Email Address");
                } else if (!et_fblogin_username.getText().toString().contains("@")
                        || !et_fblogin_username.getText().toString().contains(".com")) {
                    et_fblogin_username.setError("Please Enter Valid Input");
                }
                else if (et_fblogin_password.getText().toString().isEmpty()) {
                    et_fblogin_password.setError("Please Enter Your Password");
                } else if (et_fblogin_password.getText().toString().length() < 8) {
                    et_fblogin_password.setError("Length Must Be Greater Than 8");
                } else {
                    Toast.makeText(FacebookLoginActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}