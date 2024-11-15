package com.example.task3_loginpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView tv_songName, tv_startTime, tv_totalTime;
    ImageView img_songCover;
    ImageButton ib_previous, ib_backward, ib_play, ib_forward, ib_next;
    SeekBar sb_songProgress;
    Handler handler = new Handler();;

    MediaPlayer mediaPlayer;
    int songCurrentIndex = 0;
    private static int sTime=0, tTime=0, oTime=0, bTime=5000, fTime=5000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        tv_songName = findViewById(R.id.tv_music_player_song_name);
        tv_startTime = findViewById(R.id.tv_music_player_start_time);
        tv_totalTime = findViewById(R.id.tv_music_player_total_time);
        img_songCover = findViewById(R.id.iv_music_player_song_cover);
        ib_previous = findViewById(R.id.ib_music_player_previous);
        ib_backward = findViewById(R.id.ib_music_player_backward);
        ib_play = findViewById(R.id.ib_music_player_play);
        ib_forward = findViewById(R.id.ib_music_player_forward);
        ib_next = findViewById(R.id.ib_music_player_next);
        sb_songProgress = findViewById(R.id.sb_music_player_song_progress);

        ArrayList<Integer> songArrayList = new ArrayList<>();
        songArrayList.add(0, R.raw.blue_eyes);
        songArrayList.add(1, R.raw.afghan_jalebi);
        songArrayList.add(2, R.raw.desi_kalakaar);
        songArrayList.add(3, R.raw.satakli);
        songArrayList.add(4, R.raw.brown_rang);

        mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, songArrayList.get(songCurrentIndex));

        ib_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    ib_play.setImageResource(R.drawable.music_player_play_vector);
                } else {
                    mediaPlayer.start();
                    ib_play.setImageResource(R.drawable.music_player_pause_vector);
                }

                sTime = mediaPlayer.getCurrentPosition();
                tTime = mediaPlayer.getDuration();

                if (oTime == 0) {
                    sb_songProgress.setMax(tTime);
                    oTime = 1;
                }

                tv_startTime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))

                        ));

                tv_totalTime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tTime))

                ));


                sb_songProgress.setProgress(sTime);
                handler.postDelayed(UpdateSongTime, 1000);
                songName();
            }
        });




        ib_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songCurrentIndex > 0) {
                    songCurrentIndex--;
                } else {
                    songCurrentIndex = songArrayList.size() - 1;
                }

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                if (mediaPlayer != null) {
                    ib_play.setImageResource(R.drawable.music_player_pause_vector);
                }

                mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, songArrayList.get(songCurrentIndex));

                sTime = mediaPlayer.getCurrentPosition();
                tTime = mediaPlayer.getDuration();
                oTime = 0;

                if (oTime == 0) {
                    sb_songProgress.setMax(tTime);
                    oTime = 1;
                }

                tv_startTime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))

                ));

                tv_totalTime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tTime))

                ));


                sb_songProgress.setProgress(sTime);
                handler.postDelayed(UpdateSongTime, 1000);

                mediaPlayer.start();
                songName();
            }
        });

        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songCurrentIndex < songArrayList.size() - 1) {
                    songCurrentIndex++;
                } else {
                    songCurrentIndex = 0;
                }

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                if (mediaPlayer != null) {
                    ib_play.setImageResource(R.drawable.music_player_pause_vector);
                }

                mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, songArrayList.get(songCurrentIndex));

                sTime = mediaPlayer.getCurrentPosition();
                tTime = mediaPlayer.getDuration();
                oTime = 0;

                if (oTime == 0) {
                    sb_songProgress.setMax(tTime);
                    oTime = 1;
                }

                tv_startTime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))

                ));

                tv_totalTime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tTime))

                ));


                sb_songProgress.setProgress(sTime);
                handler.postDelayed(UpdateSongTime, 1000);

                mediaPlayer.start();
                songName();
            }
        });

        ib_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sTime - bTime) > 0) {
                    sTime -= bTime;
                    mediaPlayer.seekTo(sTime);
                } else {
                    Toast.makeText(MusicPlayerActivity.this, "Cannot Jump Backward for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ib_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sTime + fTime) < tTime) {
                    sTime += fTime;
                    mediaPlayer.seekTo(sTime);
                } else {
                    Toast.makeText(MusicPlayerActivity.this, "Cannot Jump Forward for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sb_songProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    sb_songProgress.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setTitle("Music");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);

        actionBar.setBackgroundDrawable(colorDrawable);

    }


    private void songName() {
        if (songCurrentIndex == 0) {
            tv_songName.setText("Blue Eyes");
            img_songCover.setImageResource(R.drawable.song_blue_eyes);
        } else if (songCurrentIndex == 1) {
            tv_songName.setText("Afghan Jalebi");
            img_songCover.setImageResource(R.drawable.song_afghan_jalebi);
        } else if (songCurrentIndex == 2) {
            tv_songName.setText("Desi Kalakaar");
            img_songCover.setImageResource(R.drawable.song_desi_kalakaar);
        } else if (songCurrentIndex == 3) {
            tv_songName.setText("Satakli");
            img_songCover.setImageResource(R.drawable.song_satakli);
        } else {
            tv_songName.setText("Brown Rang");
            img_songCover.setImageResource(R.drawable.song_brown_rang);
        }
    }

    public Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mediaPlayer.getCurrentPosition();

            tv_startTime.setText(String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
            ));

            sb_songProgress.setProgress(sTime);
            handler.postDelayed(this, 1000);
        }
    };

}