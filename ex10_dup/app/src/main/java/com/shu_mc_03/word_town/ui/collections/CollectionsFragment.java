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

    private String nameText;

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

        // Current Username
        try {
            nameText =  getActivity().getIntent().getStringExtra("username");
        }
        catch (NullPointerException e) {
            nameText = "";
        }

        // Load Database
        MyDatabaseHelper helper = new MyDatabaseHelper(root.getContext(), "Words.db", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] search;
        Set<String> star_set = new HashSet<>();
        Set<String> del_set = new HashSet<>();

        // RecycleView with MD_Cards
        mRecyclerView = root.findViewById(R.id.recycler);
        List<DataModel> dataModelList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(dataModelList, root.getContext());
        mRecyclerView.setAdapter(mAdapter);

        // Load Del
        SharedPreferences pref_del = getContext().getSharedPreferences("del_word"+nameText, Context.MODE_PRIVATE);
        Map<String, ?> allDeletes = pref_del.getAll();
        for (Map.Entry<String, ?> entry : allDeletes.entrySet()) {
            String[] wa_del = entry.getValue().toString().split("/");
            try {
                del_set.add(wa_del[0]+"/"+wa_del[1]);
            }
            catch (NumberFormatException e) {
                Log.e(TAG, "NO DEL WORD FOUND");
            }
        }

        // Load Star
        SharedPreferences pref_star = getContext().getSharedPreferences("star_word"+nameText, Context.MODE_PRIVATE);
        Map<String, ?> allEntries = pref_star.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String[] wa_star = entry.getValue().toString().split("/");
            Log.d(TAG, "LOAD STAR: " + Arrays.toString(wa_star));
            try {
                // Star create
                search = init_word_idx(wa_star[1], Integer.parseInt(wa_star[0]), db);
                dataModelList.add(new DataModel(search[0], search[1], true, Integer.parseInt(wa_star[0]), Integer.parseInt(wa_star[1]), nameText));
                star_set.add(wa_star[0]+"/"+wa_star[1]);
            }
            catch (NumberFormatException e) {
                Log.e(TAG, "onCreateView: ERR");
            }
            mAdapter.notifyDataSetChanged();
        }


        // Read Pref
        // Load database
        SharedPreferences pref = getContext().getSharedPreferences("current_game"+nameText, Context.MODE_PRIVATE);
        wa_0 = pref.getString("MODE_0_WAIDX", "");
        wa_1 = pref.getString("MODE_1_WAIDX", "");
        wa_2 = pref.getString("MODE_2_WAIDX", "");
        String[] wa_idx_0 = wa_0.split(", ");
        String[] wa_idx_1 = wa_1.split(", ");
        String[] wa_idx_2 = wa_2.split(", ");
        try {
            Log.d(TAG, "WA_GET_E: " + Arrays.toString(wa_idx_0));
            for (String item : wa_idx_0) {
                // Easy DB
                String[] result = init_word_idx(item, 0, db);
                if (!star_set.contains("0/"+item) && !del_set.contains("0/"+item)) {
                    dataModelList.add(new DataModel(result[0], result[1], false, 0, Integer.parseInt(item), nameText));
                }
            }
        }
        catch (NumberFormatException e) {
            Log.e(TAG, "onCreateView: NO E_WA");
        }
        try {
            Log.d(TAG, "WA_GET_N: " + Arrays.toString(wa_idx_1));
            for (String item : wa_idx_1) {
                // Normal DB
                String[] result = init_word_idx(item,1, db);
                if (!star_set.contains("1/"+item) && !del_set.contains("1/"+item)) {
                    dataModelList.add(new DataModel(result[0], result[1], false, 1, Integer.parseInt(item), nameText));
                }
            }
        }
        catch (NumberFormatException e) {
            Log.e(TAG, "onCreateView: NO N_WA");
        }
        try {
            Log.d(TAG, "WA_GET_H: " + Arrays.toString(wa_idx_2));
            for (String item : wa_idx_2) {
                // Hard DB
                String[] result = init_word_idx(item,2, db);
                if (!star_set.contains("2/"+item) && !del_set.contains("2/"+item)) {
                    dataModelList.add(new DataModel(result[0], result[1], false, 2, Integer.parseInt(item), nameText));
                }
            }
        }
        catch (NumberFormatException e) {
            Log.e(TAG, "onCreateView: NO H_WA");
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