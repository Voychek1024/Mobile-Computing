package com.shu_mc_03.illusion.ui.studio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
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

        MaterialButton button_work = root.findViewById(R.id.mat_work);
        button_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Camera/Gallery requirement
                Toast.makeText(root.getContext(), "INTENT STUDIO", Toast.LENGTH_SHORT).show();
                MaterialFilePicker()
                        // Pass a source of context. Can be:
                        //    .withActivity(Activity activity)
                        //    .withFragment(Fragment fragment)
                        //    .withSupportFragment(androidx.fragment.app.Fragment fragment)
                        .withActivity(this)
                        // With cross icon on the right side of toolbar for closing picker straight away
                        .withCloseMenu(true)
                        // Entry point path (user will start from it)
                        .withPath(alarmsFolder.absolutePath)
                        // Root path (user won't be able to come higher than it)
                        .withRootPath(externalStorage.absolutePath)
                        // Showing hidden files
                        .withHiddenFiles(true)
                        // Want to choose only jpg images
                        .withFilter(Pattern.compile(".*\\.(jpg|jpeg)$"))
                        // Don't apply filter to directories names
                        .withFilterDirectories(false)
                        .withTitle("Sample title")
                        .withRequestCode(FILE_PICKER_REQUEST_CODE)
                        .start()
            }
        });
        return root;
    }
}