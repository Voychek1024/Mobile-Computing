package com.example.a18121772_lijiaqi_week06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.text_01);

        String[] random_city = getResources().getStringArray(R.array.city_random);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand_index = new Random().nextInt(random_city.length);
                String text_set = random_city[rand_index];
                Toast t = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                text.setText(text_set);
                t.setView(layout);
                // t.setText(text_set);
                t.setGravity(Gravity.CENTER_VERTICAL, 0, -700);
                t.show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_choice, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "Testing EditText";

            @Override
            public void onClick(View v) {
                EditText text1 = (EditText) findViewById(R.id.editText);
                EditText text2 = (EditText) findViewById(R.id.editText2);
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
                if (!text1.getText().toString().equals("") && !text1.getText().toString().equals("")) {
                    if (checkBox.isChecked())
                        Log.d(TAG, "onClick() returned: " + text1.getText() + ", " + text2.getText());;
                    Toast t = Toast.makeText(MainActivity.this, "Login in", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("选择找回方式");
                builder.setItems(R.array.find_pwd_choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Toast t = Toast.makeText(MainActivity.this, "Message", Toast.LENGTH_SHORT);
                            t.show();
                        }
                        else if (which == 1) {
                            Toast t = Toast.makeText(MainActivity.this, "Email", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}