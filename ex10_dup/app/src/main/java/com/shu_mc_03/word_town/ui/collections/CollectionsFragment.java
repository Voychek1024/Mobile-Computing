package com.shu_mc_03.word_town.ui.collections;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shu_mc_03.word_town.DataModel;
import com.shu_mc_03.word_town.MyAdapter;
import com.shu_mc_03.word_town.MyDatabaseHelper;
import com.shu_mc_03.word_town.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

        // Load Star
        SharedPreferences pref_star = getContext().getSharedPreferences("star_word", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = pref_star.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Log.d(TAG, "mapped values: " + entry.getValue().toString());
            String[] wa_star = entry.getValue().toString().split(", ");
            try {
                // Star create
                dataModelList.add(new DataModel(wa_star[0], wa_star[1]));
            }
            catch (NumberFormatException e) {
                Log.e(TAG, "onCreateView: ERR");
            }
            mAdapter.notifyDataSetChanged();
        }


        // Read Pref
        // Load database
        MyDatabaseHelper helper = new MyDatabaseHelper(root.getContext(), "Words.db", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            SharedPreferences pref = getContext().getSharedPreferences("current_game", Context.MODE_PRIVATE);
            wa_0 = pref.getString("MODE_0_WAIDX", "");
            wa_1 = pref.getString("MODE_1_WAIDX", "");
            wa_2 = pref.getString("MODE_2_WAIDX", "");
            String[] wa_idx_0 = wa_0.split(", ");
            String[] wa_idx_1 = wa_1.split(", ");
            String[] wa_idx_2 = wa_2.split(", ");
            try {
                for (String item : wa_idx_0) {
                    // Easy DB
                    /*int result = Arrays.asList(idx_md).indexOf(Integer.parseInt(item));
                    dataModelList.add(new DataModel(test[result], test[result + 1]))*/;
                    Log.d(TAG, "WA_GET_E: " + Arrays.toString(wa_idx_0));
                    String[] result = init_word_idx(item, 0, db);
                    dataModelList.add(new DataModel(result[0], result[1]));
                }
            }
            catch (NumberFormatException e) {
                Log.e(TAG, "onCreateView: ERR");
            }
            try {
                for (String item : wa_idx_1) {
                    // Easy DB
                    /*int result = Arrays.asList(idx_md).indexOf(Integer.parseInt(item));
                    dataModelList.add(new DataModel(test[result], test[result + 1]))*/;
                    Log.d(TAG, "WA_GET_N: " + Arrays.toString(wa_idx_1));
                    String[] result = init_word_idx(item,1, db);
                    dataModelList.add(new DataModel(result[0], result[1]));
                }
            }
            catch (NumberFormatException e) {
                Log.e(TAG, "onCreateView: ERR");
            }
            try {
                Log.d(TAG, "WA_GET_H: " + Arrays.toString(wa_idx_2));
                for (String item : wa_idx_2) {
                    // Hard DB
                    String[] result = init_word_idx(item,2, db);
                    dataModelList.add(new DataModel(result[0], result[1]));
                }
            }
            catch (NumberFormatException e) {
                Log.e(TAG, "onCreateView: ERR");
            }
        }
        catch (Exception e) {
            Log.e(TAG, "onCreateView: ERR", e);
        }
        mAdapter.notifyDataSetChanged();
        return root;
    }

    private String[] init_word_idx(String item, int mode, SQLiteDatabase db) {
        String id, word = null, exp = null;
        if (!item.equals("")) {
            Cursor cursor = null;
            if (mode == 0)
                cursor = db.rawQuery("SELECT t.* FROM WORD_EASY t WHERE ID=?", new String[]{item});
            else if (mode == 1)
                cursor = db.rawQuery("SELECT t.* FROM WORD_NORMAL t WHERE ID=?", new String[]{item});
            else if (mode == 2)
                cursor = db.rawQuery("SELECT t.* FROM WORD_HARD t WHERE ID=?", new String[]{item});
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getString(cursor.getColumnIndex("ID"));
                    word = cursor.getString(cursor.getColumnIndex("NAME"));
                    exp = cursor.getString(cursor.getColumnIndex("EXP"));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return new String[] {word, exp};
        }
        throw new NumberFormatException();
    }
}