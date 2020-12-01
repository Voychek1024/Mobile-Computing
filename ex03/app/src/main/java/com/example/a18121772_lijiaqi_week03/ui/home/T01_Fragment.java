package com.example.a18121772_lijiaqi_week03.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.a18121772_lijiaqi_week03.R;

import org.jetbrains.annotations.NotNull;

import static android.content.ContentValues.TAG;

public class T01_Fragment extends Fragment {

    private T01_ViewModel t01_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        t01_ViewModel =
                new ViewModelProvider(this).get(T01_ViewModel.class);
        View root = inflater.inflate(R.layout.frag_task_01, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        t01_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::onClick);
        return root;
    }

    public void onClick(@NotNull View v) {
        TextView textView1 = (TextView) v.getRootView().findViewById(R.id.textView);
        EditText editText = (EditText) v.getRootView().findViewById(R.id.editTextTextPersonName);
        if (v.getId() == R.id.button) {
            // Log.i(TAG, "onClick: button");
            // Log.d(TAG, "onClick() returned: " + editText.getText());
            textView1.setText(editText.getText());
        }
    }
}