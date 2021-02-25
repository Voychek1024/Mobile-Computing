package com.shu_mc_03.word_town.ui.collections;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shu_mc_03.word_town.DataModel;
import com.shu_mc_03.word_town.MyAdapter;
import com.shu_mc_03.word_town.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CollectionsFragment extends Fragment {
    private static final String TAG = "DEBUG";

    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
    private CollectionsViewModel collectionsViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String wa_0, wa_1, wa_2;
    Set<String> wrong_answer = new HashSet<>();
    int wa_count = 0;

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

        // RecycleView with MD_Cards
        mRecyclerView = root.findViewById(R.id.recycler);
        List<DataModel> dataModelList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(dataModelList, root.getContext());
        mRecyclerView.setAdapter(mAdapter);

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
            String[] wa_idx_1 = wa_1.split(", ");
            String[] wa_idx_2 = wa_2.split(", ");
            for (String item : wa_idx_0) {
                // Easy DB
                int result = Arrays.asList(idx_md).indexOf(Integer.parseInt(item));
                dataModelList.add(new DataModel(test[result], test[result + 1]));
            }
            for (String item : wa_idx_1) {
                // Normal DB
                int result = Arrays.asList(idx_md).indexOf(Integer.parseInt(item));
                dataModelList.add(new DataModel(test[result], test[result + 1]));
            }
            for (String item : wa_idx_2) {
                // Hard DB
                int result = Arrays.asList(idx_md).indexOf(Integer.parseInt(item));
                dataModelList.add(new DataModel(test[result], test[result + 1]));
            }
        }
        catch (Exception e) {
            Log.e(TAG, "onCreateView: ERR", e);
        }
        mAdapter.notifyDataSetChanged();
        return root;
    }
}