package com.shu_mc_03.word_town;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class Board extends AppCompatActivity {

    private static final String TAG = "DEBUG";
    int time_usage = 0;
    boolean on_play = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        int mode = getIntent().getIntExtra("MODE", 0);
        Log.d(TAG, "onCreate() returned: GAMEMODE=" + mode);
        String[] color = {};
        String[] test = {"0","1","2","3","4","5","6","7","0","1","2","3","4","5","6","7"};
        Button[] buttons =
                {findViewById(R.id.button_0_0), findViewById(R.id.button_0_1), findViewById(R.id.button_0_2), findViewById(R.id.button_0_3),
                        findViewById(R.id.button_1_0), findViewById(R.id.button_1_1), findViewById(R.id.button_1_2), findViewById(R.id.button_1_3),
                        findViewById(R.id.button_2_0), findViewById(R.id.button_2_1), findViewById(R.id.button_2_2), findViewById(R.id.button_2_3),
                        findViewById(R.id.button_3_0), findViewById(R.id.button_3_1), findViewById(R.id.button_3_2), findViewById(R.id.button_3_3)};
        if (mode == 0) {
            fisher_randomize(test, test.length);
            Log.d(TAG, "SHUFFLED List: " + Arrays.toString(test));
            int idx = 0;
            for (Button button : buttons) {
                button.setText(test[idx]);
                idx++;
                // TODO: bind buttons with judgement functions
                    // TODO: Destroy Animations?
            }
        }
        // TODO: other MODE implementations

        // Updates Total Time Usage
        TextView time_text = findViewById(R.id.time_usage);
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                time_usage++;
                time_text.setText(Integer.toString(time_usage) + "s");
            }
        };
        handler.postDelayed(r, 0);

        Button button_music = (Button) findViewById(R.id.music_playback);
        button_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_play = !on_play;
                // TODO: Control Music Playback
                Log.d(TAG, "onClick() returned: MUSIC CONTROL: " + on_play);
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged() returned: SOUND CONTROL" + progress);
                // TODO: Music Volume Control
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    static void fisher_randomize(String[] arr, int n) {
        Random r = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}