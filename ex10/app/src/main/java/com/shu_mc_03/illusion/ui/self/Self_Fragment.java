package com.shu_mc_03.illusion.ui.self;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.shu_mc_03.illusion.R;
import com.shu_mc_03.illusion.SettingsActivity;
import com.shu_mc_03.illusion.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Self_Fragment extends Fragment {
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    private Self_ViewModel selfViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        selfViewModel =
                new ViewModelProvider(this).get(Self_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_self, container, false);
        final TextView textView = root.findViewById(R.id.text_self);
        selfViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
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
        Intent intent_setting = new Intent(root.getContext(), SettingsActivity.class);
        MaterialButton button_settings = root.findViewById(R.id.mat_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.getContext().startActivity(intent_setting);
            }
        });
        Intent intent_login = new Intent(root.getContext(), LoginActivity.class);
        MaterialButton button_login = root.findViewById(R.id.prompt_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Login prompt implementation
                root.getContext().startActivity(intent_login);
            }
        });
        return root;
    }
}
