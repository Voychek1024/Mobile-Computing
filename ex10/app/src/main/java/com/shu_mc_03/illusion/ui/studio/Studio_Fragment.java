package com.shu_mc_03.illusion.ui.studio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.shu_mc_03.illusion.R;

public class Studio_Fragment extends Fragment {

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
        return root;
    }
}