package com.shu_mc_03.illusion.ui.self;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.shu_mc_03.illusion.R;

public class Self_Fragment extends Fragment {
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
        return root;
    }
}
