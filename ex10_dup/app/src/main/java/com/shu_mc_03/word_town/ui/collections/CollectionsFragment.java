package com.shu_mc_03.word_town.ui.collections;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CollectionsFragment extends Fragment {
    private static final String TAG = "DEBUG";

    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
    private CollectionsViewModel collectionsViewModel;

    String wa_0, wa_1, wa_2;
    Set<String> wrong_answer = new HashSet<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        collectionsViewModel =
                new ViewModelProvider(this).get(CollectionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collections, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        collectionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        // Read Pref
        // Test Data
        String[] test = {"zero","0","one","1","two","2","three","3","four","4","five","5","six","6","seven","7"};
        Integer[] idx_md = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};
        try {
            SharedPreferences pref = getContext().getSharedPreferences("current_game", Context.MODE_PRIVATE);
            wa_0 = pref.getString("MODE_0_WAIDX", "");
            wa_1 = pref.getString("MODE_1_WAIDX", "");
            wa_2 = pref.getString("MODE_2_WAIDX", "");
            String[] wa_idx_0 = wa_0.split(", ");
            for (String item : wa_idx_0) {
                int result = Arrays.asList(idx_md).indexOf(Integer.parseInt(item));
                String result_item = test[result] + " " + test[result + 1];
                Log.d(TAG, "CREATE WA_LIST: " + result_item);
            }
            Log.d(TAG, "Read Pref: " + Arrays.asList(wa_0).get(0));
        }
        catch (Exception e) {
            Log.e(TAG, "onCreateView: ERR", e);
        }

        // TODO: Two-lined list with icon

        return root;
    }
}