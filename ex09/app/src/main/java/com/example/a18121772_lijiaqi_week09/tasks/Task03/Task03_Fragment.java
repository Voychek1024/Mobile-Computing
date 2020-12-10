package com.example.a18121772_lijiaqi_week09.tasks.Task03;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a18121772_lijiaqi_week09.R;

public class Task03_Fragment extends Fragment {
    private int random_idx = 0;

    private static final String TAG = "LOG";
    private Task03_ViewModel task03ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task03ViewModel =
                new ViewModelProvider(this).get(Task03_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_task03, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        task03ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        SeekBar seekBar = (SeekBar) root.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged() returned: " + progress);
                random_idx = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button button_update = (Button) root.findViewById(R.id.button_update);
        Button button_show = (Button) root.findViewById(R.id.button_show);
        

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }
}