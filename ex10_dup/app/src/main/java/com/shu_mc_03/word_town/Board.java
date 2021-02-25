package com.shu_mc_03.word_town;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Board extends AppCompatActivity {

    private static final String TAG = "DEBUG";
    int time_usage = 0;
    boolean on_play = true;
    int click_count = 0;
    Stack<Integer> stack_cp = new Stack<Integer>();
    Stack<Button> btn_cp = new Stack<Button>();
    int score = 0;
    int score_cal = 0;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        int mode = getIntent().getIntExtra("MODE", 0);
        Log.d(TAG, "onCreate() returned: GAMEMODE=" + mode);
        String[] color = {"#5B9D74", "#FFE666", "#7D93C8", "#364E4A", "#196432", "#7D93C8", "#C9B8FF", "#F5C27D",
                "#5B9D74", "#FFE666", "#7D93C8", "#364E4A", "#196432", "#7D93C8", "#C9B8FF", "#F5C27D"};
        String[] test = {"0","1","2","3","4","5","6","7","zero","one","two","three","four","five","six","seven"};
        Integer[] idx_md = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
        Button[] buttons =
                {findViewById(R.id.button_0_0), findViewById(R.id.button_0_1), findViewById(R.id.button_0_2), findViewById(R.id.button_0_3),
                        findViewById(R.id.button_1_0), findViewById(R.id.button_1_1), findViewById(R.id.button_1_2), findViewById(R.id.button_1_3),
                        findViewById(R.id.button_2_0), findViewById(R.id.button_2_1), findViewById(R.id.button_2_2), findViewById(R.id.button_2_3),
                        findViewById(R.id.button_3_0), findViewById(R.id.button_3_1), findViewById(R.id.button_3_2), findViewById(R.id.button_3_3)};
        TextView score_dt = (TextView) findViewById(R.id.dynamic_score);
        RelativeLayout result = (RelativeLayout) findViewById(R.id.relativeLayout3);
        result.setAlpha(0.0f);
        Button button_share = (Button) findViewById(R.id.prompt_share);
        Button button_quit = (Button) findViewById(R.id.prompt_quit);
        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: SharedPreferences Store: gamemode, score, wrong answer idx
                SharedPreferences.Editor editor = getSharedPreferences("current_game", MODE_PRIVATE).edit();
                editor.putString("MODE_"+Integer.toString(mode), String.valueOf(score_cal));
                editor.putString("MODE_"+Integer.toString(mode)+"_WAIDX", "None");
                editor.apply();
                Log.d(TAG, "onClick() button_quit: " + "RETURN TO MENU");
                Toast.makeText(getBaseContext(), "Game Saved.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Screenshot
            }
        });
        button_quit.setClickable(false);
        button_share.setClickable(false);
        TextView total_time = (TextView) findViewById(R.id.time_total);
        TextView total_score = (TextView) findViewById(R.id.total_score);

        // Background Music
        player = MediaPlayer.create(this, R.raw.bankrupt_sea);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();

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

        // Game Model implementation
        Integer[] shadow = {};
        fisher_randomize(shadow, color, color.length);
        if (mode == 0) {
            fisher_randomize(idx_md, test, test.length);
            Log.d(TAG, "SHUFFLED List: " + Arrays.toString(test));
            Log.d(TAG, "Index Markdown: " + Arrays.toString(idx_md));
            int idx = 0;
            for (Button button : buttons) {
                button.setText(test[idx]);
                button.setBackgroundColor(Color.parseColor(color[idx]));
                idx++;
                button.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        click_count += 1;
                        stack_cp.push(idx_md[Arrays.asList(test).indexOf(button.getText().toString())]);
                        Log.d(TAG, "Pushed Button: " + button.getId());
                        btn_cp.push(button);
                        Log.d(TAG, "Judgement Stack: " + stack_cp);

                        if (click_count == 2) {
                            click_count = 0;
                            Log.d(TAG, "Judgement Stack Final: " + stack_cp);
                            if (btn_cp.get(0).equals(btn_cp.get(1))) {
                                Log.d(TAG, "Game Judge: " + "Same....");
                            }
                            else if (stack_cp.get(0).equals(stack_cp.get(1))) {
                                Log.d(TAG, "Game Judge: " + "Correct!");
                                // TODO: Destroy Animations?
                                btn_cp.get(0).setAlpha(0.0f);
                                btn_cp.get(1).setAlpha(0.0f);
                                btn_cp.get(0).setClickable(false);
                                btn_cp.get(1).setClickable(false);
                                score += 2;
                                score_dt.setText(score + "pts");
                                if (score == 16) {
                                    handler.removeCallbacks(r);
                                    // Pop Game Result
                                    player.stop();
                                    total_time.setText(Integer.toString(time_usage));
                                    score_cal = (int) (score * (1.0/time_usage) * 500);
                                    total_score.setText(Integer.toString(score_cal));
                                    result.setAlpha(1.0f);
                                    button_share.setClickable(true);
                                    button_quit.setClickable(true);
                                }
                            }
                            else {
                                Log.d(TAG, "Game Judge: " + "Wrong...");
                            }
                            stack_cp.clear();
                            btn_cp.clear();
                        }
                    }
                });
            }
        }
        // TODO: other MODE implementations

        Button button_music = (Button) findViewById(R.id.music_playback);
        button_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_play = !on_play;
                // Control Music Playback
                if (on_play)
                    player.start();
                else
                    player.pause();
                Log.d(TAG, "onClick() returned: MUSIC CONTROL: " + on_play);
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Music Volume Control
                float vol = (float) ((100.0 - (4.0 - progress) * 25.0) / 100.0);
                Log.d(TAG, "onProgressChanged() returned: SOUND CONTROL: " + vol);
                player.setVolume(vol, vol);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    static void fisher_randomize(Integer[] idx, String[] arr, int n) {
        Random r = new Random();
        if (idx.length != 0) {
            for (int i = n - 1; i > 0; i--) {
                int j = r.nextInt(i + 1);
                String temp = arr[i];
                int tmp = idx[i];
                arr[i] = arr[j];
                idx[i] = idx[j];
                arr[j] = temp;
                idx[j] = tmp;
            }
        }
        else {
            for (int i = n - 1; i > 0; i--) {
                int j = r.nextInt(i + 1);
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}