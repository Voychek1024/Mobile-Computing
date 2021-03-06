package com.shu_mc_03.word_town;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shu_mc_03.word_town.utils.ShotShareUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.crypto.Cipher;

import tyrantgit.explosionfield.ExplosionField;

public class Board extends AppCompatActivity {

    private static final String TAG = "DEBUG";

    boolean doubleBackToExitPressedOnce = false;

    int time_usage = 0;
    boolean on_play = true;
    int click_count = 0;
    int correct_3 = 0;
    Stack<String> stack_cp = new Stack<String>();
    Stack<Button> btn_cp = new Stack<Button>();
    int score = 0;
    int score_cal = 0;
    Activity activity;
    MediaPlayer player;
    Set<String> wrong_answer = new HashSet<String>();

    private MyDatabaseHelper helper;
    List<String> test = new ArrayList<>();
    List<String> idx_md = new ArrayList<>();

    private ExplosionField explosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_board);

        int mode = getIntent().getIntExtra("MODE", 0);
        Log.d(TAG, "onCreate() returned: GAMEMODE=" + mode);

        // Load SFX
        List<MediaPlayer> players = Arrays.asList(MediaPlayer.create(this, R.raw.error),
                MediaPlayer.create(this, R.raw.great),
                MediaPlayer.create(this, R.raw.success),
                MediaPlayer.create(this, R.raw.photo));
        for (MediaPlayer _player : players) {
            _player.setLooping(false);
            _player.setVolume(100, 100);
        }

        List<String> color = Arrays.asList("#5B9D74", "#FFE666", "#7D93C8", "#364E4A", "#196432", "#7D93C8", "#C9B8FF", "#F5C27D",
                "#5B9D74", "#FFE666", "#7D93C8", "#364E4A", "#196432", "#7D93C8", "#C9B8FF", "#F5C27D");
        List<Button> buttons = Arrays.asList(findViewById(R.id.button_0_0), findViewById(R.id.button_0_1), findViewById(R.id.button_0_2), findViewById(R.id.button_0_3),
                        findViewById(R.id.button_1_0), findViewById(R.id.button_1_1), findViewById(R.id.button_1_2), findViewById(R.id.button_1_3),
                        findViewById(R.id.button_2_0), findViewById(R.id.button_2_1), findViewById(R.id.button_2_2), findViewById(R.id.button_2_3),
                        findViewById(R.id.button_3_0), findViewById(R.id.button_3_1), findViewById(R.id.button_3_2), findViewById(R.id.button_3_3));
        TextView score_dt = (TextView) findViewById(R.id.dynamic_score);
        RelativeLayout result = (RelativeLayout) findViewById(R.id.relativeLayout3);
        result.setAlpha(0.0f);
        Button button_share = (Button) findViewById(R.id.prompt_share);
        Button button_quit = (Button) findViewById(R.id.prompt_quit);
        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Show the username
                Intent present = getIntent();
                String nameText = present.getStringExtra("username");
                if(nameText == null)
                    nameText = "";
                Log.d(TAG, "USERNAME: " + nameText);

                // SharedPreferences Store: gamemode, score, wrong answer idx
                SharedPreferences.Editor editor = getSharedPreferences("current_game"+nameText, MODE_PRIVATE).edit();
                editor.putString("MODE_"+Integer.toString(mode), String.valueOf(score_cal));
                String wa_write = String.valueOf(wrong_answer);
                wa_write = wa_write.substring(1,wa_write.length()-1);
                editor.putString("MODE_"+Integer.toString(mode)+"_WAIDX", wa_write);
                editor.apply();
                Log.d(TAG, "Game Wrong Answer: " + wa_write);
                Log.d(TAG, "onClick() button_quit: " + "RETURN TO MENU");
                Toast.makeText(getBaseContext(), "Game Saved.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Screenshot
                players.get(3).start();
                ShotShareUtil.shotShare(activity);
            }
        });
        button_quit.setClickable(false);
        button_share.setClickable(false);
        TextView total_time = (TextView) findViewById(R.id.time_total);
        TextView total_score = (TextView) findViewById(R.id.total_score);

        // Background Music
        player = MediaPlayer.create(this, R.raw.aquarium);
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
        wrong_answer.clear();
        helper = new MyDatabaseHelper(getBaseContext(), "Words.db", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        fisher_randomize(null, color);
        init_words(idx_md, test, mode, db);
        fisher_randomize(idx_md, test);

        // Animations
        explosionField = ExplosionField.attach2Window(this);
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        int idx = 0;
        for (Button button : buttons) {
            button.setText(test.get(idx));
            button.setBackgroundColor(Color.parseColor(color.get(idx)));
            idx++;
            button.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    click_count += 1;
                    stack_cp.push(idx_md.get(test.indexOf(button.getText().toString())));
                    Log.d(TAG, "Pushed Button: " + button.getId());
                    btn_cp.push(button);
                    Log.d(TAG, "Judgement Stack: " + stack_cp);
                    String color_old = color.get(buttons.indexOf(button));
                    button.setBackgroundColor(Color.parseColor("#C0C0C0"));
                    if (click_count == 2) {
                        click_count = 0;
                        Log.d(TAG, "Judgement Stack Final: " + stack_cp);
                        if (btn_cp.get(0).equals(btn_cp.get(1))) {
                            Log.d(TAG, "Game Judge: " + "Same....");
                            btn_cp.get(0).setBackgroundColor(Color.parseColor(color_old));
                        }
                        else if (stack_cp.get(0).equals(stack_cp.get(1))) {
                            Log.d(TAG, "Game Judge: " + "Correct!");
                            correct_3 += 1;
                            if (correct_3 == 3) {
                                players.get(1).start();
                                correct_3 = 0;
                            }
                            // Destroy Animations
                            explosionField.explode(btn_cp.get(0));
                            explosionField.explode(btn_cp.get(1));
                            btn_cp.get(0).setClickable(false);
                            btn_cp.get(1).setClickable(false);
                            score += 2;
                            score_dt.setText(score + "pts");
                            if (score == 16) {
                                handler.removeCallbacks(r);
                                // Pop Game Result
                                players.get(2).start();
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
                            correct_3 = 0;
                            players.get(0).start();
                            wrong_answer.add(stack_cp.get(0));
                            wrong_answer.add(stack_cp.get(1));
                            btn_cp.get(0).startAnimation(animShake);
                            btn_cp.get(1).startAnimation(animShake);
                            switch_color(buttons, color);
                        }
                        stack_cp.clear();
                        btn_cp.clear();
                    }
                }
            });
        }

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

    private void init_words(List<String> idx_md, List<String> test, int mode, SQLiteDatabase db) {
        Random r = new Random();
        while (idx_md.size() != 16) {
            Cursor cursor = null;
            if (mode == 0) {
                int j = r.nextInt(5518 + 1);
                cursor = db.rawQuery("SELECT t.* FROM WORD_EASY t WHERE ID=?", new String[]{String.valueOf(j)});
            }
            else if (mode == 1) {
                int j = r.nextInt(4610 + 1);
                cursor = db.rawQuery("SELECT t.* FROM WORD_NORMAL t WHERE ID=?", new String[]{String.valueOf(j)});
            }
            else if (mode == 2) {
                int j = r.nextInt(2219 + 1);
                cursor = db.rawQuery("SELECT t.* FROM WORD_HARD t WHERE ID=?", new String[]{String.valueOf(j)});
            }
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            String word = cursor.getString(cursor.getColumnIndex("NAME"));
            String exp = cursor.getString(cursor.getColumnIndex("EXP"));
            idx_md.add(id);
            test.add(word);
            idx_md.add(id);
            test.add(exp);
            cursor.close();
        }
    }

    static void fisher_randomize(@Nullable List<String> idx, List<String> arr) {
        Random r = new Random();
        if (idx != null) {
            int n = idx.size();
            for (int i = n - 1; i > 0; i--) {
                int j = r.nextInt(i + 1);
                String temp = arr.get(i);
                String tmp = idx.get(i);
                arr.set(i, arr.get(j));
                idx.set(i, idx.get(j));
                arr.set(j, temp);
                idx.set(j, tmp);
            }
        }
        else {
            int n = arr.size();
            for (int i = n - 1; i > 0; i--) {
                int j = r.nextInt(i + 1);
                String temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
    }

    private void switch_color(List<Button> buttons, List<String> colors) {
        int j = 0;
        Random r = new Random();
        for (int i = 16 - 1; i > 0; i--) {
            int k = r.nextInt(i + 1);
            String temp = colors.get(i);
            colors.set(i, colors.get(k));
            colors.set(k, temp);
        }
        for (Button button : buttons) {
            button.setBackgroundColor(Color.parseColor(colors.get(j)));
            j += 1;
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            player.stop();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}