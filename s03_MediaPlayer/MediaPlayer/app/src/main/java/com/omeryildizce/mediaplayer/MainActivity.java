package com.omeryildizce.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button buttonforward, buttonBackward, buttonPlay, buttonPause;
    TextView timeText, songTitle;
    SeekBar seekBar;

    MediaPlayer mediaPlayer;

    Handler handler = new Handler();

    double startTime = 0;
    double finalTime = 0;
    int forwardTime = 10_000;
    int backardTime = 10_000;
    static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonforward = findViewById(R.id.buttonforward);
        buttonBackward = findViewById(R.id.buttonBackward);

        songTitle = findViewById(R.id.songTitle);
        timeText = findViewById(R.id.time_left_text);

        seekBar = findViewById(R.id.seekBar);

        mediaPlayer = MediaPlayer.create(this, R.raw.astronaut);

        songTitle.setText(getResources().getIdentifier(
                "astronaut",
                "raw",
                getPackageName()
        ));
        seekBar.setClickable(false);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayMusic();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        buttonforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if (temp + forwardTime <= finalTime)
                {
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime)  ;
                }
            }
        });

        buttonBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if (temp-backardTime > 0){
                    startTime = startTime -backardTime;
                    mediaPlayer.seekTo((int) startTime);
                }
            }
        });

    }

    private void PlayMusic() {
        mediaPlayer.start();
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekBar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }
        timeText.setText(String.format("%2d min, %2d sec", TimeUnit.MILLISECONDS.toMinutes((long) finalTime), TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
        seekBar.setProgress((int) startTime);
        handler.postDelayed(UpdateSongTime, 100);


    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            timeText.setText(String.format("%2d min, %2d sec", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

            seekBar.setProgress((int)startTime);
            handler.postDelayed(this, 100);
        }
    };
}
