package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    EditText timerTextView;
    SeekBar timerSeekBar;
    boolean active=false;
    CountDownTimer countTimer;
    Button go;
    public void buttonClicked (View view) {

        if (active) {
            timerTextView.setText("0:30");
            timerSeekBar.setEnabled(true);
            timerSeekBar.setProgress(30);
            countTimer.cancel();
            go.setText("Go");
            active=false;
        }
        else {
            active = true;
            timerSeekBar.setEnabled(false);
            go.setText("STOP");
            countTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.video);
                    mplayer.start();
                }
            }.start();
        }
    }
    public void updateTimer(int secondLeft){
        int minutes = secondLeft/60;
        int second = secondLeft - (minutes * 60);
        String sec;
        sec= Integer.toString(second);
        if(second<=9)
            sec="0"+ sec;
        timerTextView.setText(Integer.toString(minutes) + ":" + sec);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar   = findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.textView);
        go = findViewById(R.id.button);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}