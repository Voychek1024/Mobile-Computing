package com.shu_mc_03.illusion.ui.studio;

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

import com.shu_mc_03.illusion.R;

import java.util.Timer;
import java.util.TimerTask;

public class Studio_Fragment extends Fragment {
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    private Studio_ViewModel studioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studioViewModel =
                new ViewModelProvider(this).get(Studio_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_studio, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        studioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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