package com.shu_mc_03.word_town.ui.self;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.shu_mc_03.word_town.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SelfFragment extends Fragment {
    private static final String TAG = "DEBUG";
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    String mode_0_cur, mode_1_cur, mode_2_cur;
    String mode_0, mode_1, mode_2;

    private SelfViewModel selfViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        selfViewModel =
                new ViewModelProvider(this).get(SelfViewModel.class);
        View root = inflater.inflate(R.layout.fragment_self, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        selfViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        // Highest Score Board
        ListView listView = (ListView) root.findViewById(R.id.list_score_board);
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
        listView.setAdapter(adapter);
        try {
            SharedPreferences pref_cp = getContext().getSharedPreferences("current_game", Context.MODE_PRIVATE);
            mode_0_cur = pref_cp.getString("MODE_0","0");
            mode_1_cur = pref_cp.getString("MODE_1","0");
            mode_2_cur = pref_cp.getString("MODE_2","0");

            SharedPreferences pref = getContext().getSharedPreferences("total_data", Context.MODE_PRIVATE);
            mode_0 = pref.getString("MODE_0", "0");
            mode_1 = pref.getString("MODE_1", "0");
            mode_2 = pref.getString("MODE_2", "0");

            list.clear();
            list.add("Easy Mode:\t" + (Integer.parseInt(mode_0_cur)>Integer.parseInt(mode_0)?mode_0_cur:mode_0));
            list.add("Normal Mode:\t" + (Integer.parseInt(mode_1_cur)>Integer.parseInt(mode_1)?mode_1_cur:mode_1));
            list.add("Hard Mode:\t" + (Integer.parseInt(mode_2_cur)>Integer.parseInt(mode_2)?mode_2_cur:mode_2));
            adapter.notifyDataSetChanged();

            SharedPreferences.Editor editor = getContext().getSharedPreferences("total_data", Context.MODE_PRIVATE).edit();
            editor.putString("MODE_0", list.get(0).substring("Easy Mode:\t".length()));
            editor.putString("MODE_1", list.get(1).substring("Normal Mode:\t".length()));
            editor.putString("MODE_2", list.get(2).substring("Hard Mode:\t".length()));
            editor.apply();
        }
        catch (NullPointerException e) {
            Log.e(TAG, "PREF ERR: ", e);
        }

        // Etc: Clear all data
        MaterialButton button_clr = (MaterialButton) root.findViewById(R.id.mat_clear_all);
        button_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Clear All Data")
                        .setMessage("Confirm?\n(This operation is not recoverable.)")
                        .setIcon(R.drawable.ic_baseline_delete_forever_24)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Deleting Prefs
                                SharedPreferences pref_1 = getContext().getSharedPreferences("current_game", Context.MODE_PRIVATE);
                                pref_1.edit().clear().apply();
                                SharedPreferences pref_2 = getContext().getSharedPreferences("total_data", Context.MODE_PRIVATE);
                                pref_2.edit().clear().apply();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        // TODO: Login Service

        return root;
    }
}