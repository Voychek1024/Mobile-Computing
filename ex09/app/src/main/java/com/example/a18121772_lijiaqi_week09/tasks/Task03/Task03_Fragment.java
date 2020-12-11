package com.example.a18121772_lijiaqi_week09.tasks.Task03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a18121772_lijiaqi_week09.MyDatabaseHelper;
import com.example.a18121772_lijiaqi_week09.R;

import java.util.ArrayList;
import java.util.Random;

public class Task03_Fragment extends Fragment {
    private int random_idx = 0;
    private MyDatabaseHelper dbHelper;
    private static final String TAG = "LOG";
    private Task03_ViewModel task03ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task03ViewModel =
                new ViewModelProvider(this).get(Task03_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_task03, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        task03ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        SeekBar seekBar = (SeekBar) root.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged() returned: " + progress);
                random_idx = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        Button button_update = (Button) root.findViewById(R.id.button_update);
        Button button_show = (Button) root.findViewById(R.id.button_show);

        dbHelper = new MyDatabaseHelper(getContext(), "WareHouse.db", null, 2);

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("delete from storage");
                for (int i = 0; i < random_idx; i++) {
                    ContentValues values = new ContentValues();
                    String name = "Good" + String.valueOf(i + 1);
                    values.put("name",name);
                    values.put("specification", random_com());
                    values.put("price", (int)(Math.random() * ((1000 - 1) + 1)) + 1);
                    values.put("quantity", (int)(Math.random() * ((100 - 1) + 1)) + 1);
                    Log.d(TAG, "onClick() returned: " + values);
                    db.insert("storage",null,values);
                }
                Toast.makeText(getContext(), "UPDATE SUCCESS", Toast.LENGTH_SHORT).show();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.list_view);
        ArrayList<String> list = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
        listView.setAdapter(adapter);

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select t.name, t.specification, t.price, t.quantity from storage t", null);
                list.clear();
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String spec = cursor.getString(cursor.getColumnIndex("specification"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        String quantity = cursor.getString(cursor.getColumnIndex("quantity"));
                        Log.d(TAG, "onClick() returned: " + name + " | " + spec + " | " + price + " | " + quantity);
                        list.add(name + " | " + spec + " | " + price + " | " + quantity);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }

    private String random_com() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int a =  (int)(Math.random() * ((90 - 65) + 1)) + 65;
            builder.append((char)a);
        }
        builder.append((int)(Math.random() * ((9999 - 1000) + 1)) + 1000);
        return builder.toString();
    }
}