package com.example.a18121772_lijiaqi_week08;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView_title = (TextView) findViewById(R.id.news_title);
        TextView textView_content = (TextView) findViewById(R.id.news_content);
        // List Adapter and OnItemClickListener
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_item_show, R.layout.support_simple_spinner_dropdown_item);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                String string = String.valueOf(position+1);
                // Toast.makeText(getBaseContext(), string, Toast.LENGTH_LONG).show();
                textView_title.setText("NEWS" + string);
                textView_content.setText("This is the content of NEWS" + string);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Register Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Registeration.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}