package com.shu_mc_03.illusion.ui.studio;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import static android.app.Activity.RESULT_OK;

public class Studio_Fragment extends Fragment {
    private static final String TAG = "SYS_DEBUG";

    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    private Studio_ViewModel studioViewModel;

    static final int REQUEST_IMAGE_OPEN = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getActivity().getPackageManager())!=null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            Log.i(TAG, "onActivityResult: Success Return URI");
            // TODO: more actions
        }
        else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            Uri fullPhotoUri = data.getData();
            Log.i(TAG, "onActivityResult: Success Return URI Cap");
            // TODO: more actions
        }
    }

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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("SELECT ACTION")
                        .setItems(R.array.choose_action, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0)
                                    capturePhoto();
                                if (which == 1)
                                    selectImage();
                            }
                        });
                builder.create();
                builder.show();
            }
        });
        return root;
    }
}