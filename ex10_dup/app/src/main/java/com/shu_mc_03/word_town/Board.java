package com.shu_mc_03.word_town;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Board extends AppCompatActivity {

    private static final String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        int mode = getIntent().getIntExtra("MODE", 0);
        Log.d(TAG, "onCreate() returned: " + mode);
    }
}