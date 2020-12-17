package com.example.a18121772_lijiaqi_week03.ui.Task00;

import android.os.Bundle;
import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.a18121772_lijiaqi_week03.R;

public class T00_Fragment extends Fragment {

    private T00_ViewModel t00_viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        t00_viewModel = new ViewModelProvider(this).get(T00_ViewModel.class);
        View root = inflater.inflate(R.layout.frag_task_00, container, false);
        final TextView textView = root.findViewById(R.id.text_task_zero);
        t00_viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        SeekBar seekBar = root.findViewById(R.id.seekBar);
        TextView text_val = root.findViewById(R.id.val_view);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_val.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        CheckBox checkBox_active = root.findViewById(R.id.checkBox_active);
        CheckBox checkBox_passive = root.findViewById(R.id.checkBox_passive);
        checkBox_active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    checkBox_passive.setChecked(true);
            }
        });
        RadioButton radio_1 = root.findViewById(R.id.radioButton_1);
        RadioButton radio_2 = root.findViewById(R.id.radioButton_2);
        radio_1.setChecked(true);
        radio_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    radio_2.setChecked(false);
            }
        });
        radio_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    radio_1.setChecked(false);
            }
        });
        Button button = root.findViewById(R.id.button);
        ProgressBar progressBar = root.findViewById(R.id.progressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                if (progressBar.getAlpha() == 0.0f)
                    progressBar.setAlpha(1.0f);
                else
                    progressBar.setAlpha(0.0f);
            }
        });
        return root;
    }
}
