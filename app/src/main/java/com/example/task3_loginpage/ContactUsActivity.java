package com.example.task3_loginpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUsActivity extends AppCompatActivity {

    EditText et_mob_no, et_msg;
    Button btn_send_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ActivityCompat.requestPermissions(ContactUsActivity.this, new String[] {Manifest.permission.SEND_SMS}, 90);

        et_mob_no = findViewById(R.id.et_contact_us_mobile_no);
        et_msg = findViewById(R.id.et_contact_us_enter_your_msg);
        btn_send_sms = findViewById(R.id.btn_contact_us_send_sms);

        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_mobile_no = et_mob_no.getText().toString();
                String str_msg = et_msg.getText().toString();

                Intent i = new Intent(ContactUsActivity.this, HomeActivity.class);

                PendingIntent pi = PendingIntent.getActivity(ContactUsActivity.this, 1, i, PendingIntent.FLAG_IMMUTABLE);

                // Sent sms
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(str_mobile_no, null, str_msg, pi, null);
            }
        });

        setTitle("Contact Us");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);

        actionBar.setBackgroundDrawable(colorDrawable);
    }
}