package com.shu_mc_03.word_town.ui.self;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.shu_mc_03.word_town.R;

import java.util.Timer;
import java.util.TimerTask;

public class SelfFragment extends Fragment {
    private static final String TAG = "DEBUG";

    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    private SelfViewModel selfViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        selfViewModel =
                new ViewModelProvider(this).get(SelfViewModel.class);
        View root = inflater.inflate(R.layout.fragment_self, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        selfViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        fadeOut.setDuration(500);
        fadeOut.setFillAfter(true);

        textView.setAlpha(1.0f);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                textView.startAnimation(fadeOut);
            }
        };
        timer.schedule(task, 1500);

        return root;
    }
}