package com.example.task3_loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {

    EditText et_enter_your_text;
    SeekBar sb_pitch, sb_speed;
    Button btn_speak;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        et_enter_your_text = findViewById(R.id.et_text_to_speech_enter_text);
        sb_pitch = findViewById(R.id.sb_text_to_speech_pitch);
        sb_speed = findViewById(R.id.sb_text_to_speech_speed);
        btn_speak = findViewById(R.id.btn_text_to_speech_speak);

        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakText();
            }
        });

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(TextToSpeechActivity.this, "This language is not supported", Toast.LENGTH_SHORT).show();
                    } else {
                        speakText();
                    }
                }
                else {
                    Toast.makeText(TextToSpeechActivity.this, "Failed to initialize", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void speakText() {
        String text = et_enter_your_text.getText().toString();

        float pitch = (float) sb_pitch.getProgress()/50;
        if (pitch < 0.1f) {
            pitch = 0.1f;
        }
        textToSpeech.setPitch(pitch);
        float speed = (float) sb_speed.getProgress()/50;
        if (speed < 0.1f) {
            speed = 0.1f;
        }
        textToSpeech.setSpeechRate(speed);

        if ("".equals(text)) {
            text = "Please enter some text to speak";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

        }
    }
}