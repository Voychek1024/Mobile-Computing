package com.example.a18121772_lijiaqi_week09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;

public class Data_Form extends AppCompatActivity {
    private static final String TAG = "LOG";
    private String account;
    private String nickname = "";
    private String gender = "";
    private String age = "";
    private String hobby = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__form);

        Spinner spinner_gen = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter_gen = ArrayAdapter.createFromResource(this, R.array.gender_choice, R.layout.support_simple_spinner_dropdown_item);
        adapter_gen.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_gen.setAdapter(adapter_gen);

        ArrayList<String> ages = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1960; i--) {
            ages.add(Integer.toString(thisYear - i));
        }
        ArrayAdapter<String> adapter_ages = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ages);
        Spinner spinner_ages = (Spinner) findViewById(R.id.spinner_age);
        spinner_ages.setAdapter(adapter_ages);

        Button button_act = (Button) findViewById(R.id.button_action);
        Button button_bak = (Button) findViewById(R.id.button_back);

        EditText edit_nickname = (EditText) findViewById(R.id.edit_nickname);
        EditText edit_hobby = (EditText) findViewById(R.id.edit_hobby);
        TextView view_account = (TextView) findViewById(R.id.view_account_account);

        Intent intent = getIntent();
        String stat = intent.getStringExtra("Status");

        if (stat.equals("0")) {
            button_act.setText(R.string.prompt_submit);
        }
        else {
            account = intent.getStringExtra("current_acc");
            try {
                SharedPreferences pref = getSharedPreferences(account, MODE_PRIVATE);
                button_act.setText(R.string.prompt_update);
                nickname = pref.getString("Nickname","");
                gender = pref.getString("Gender","");
                age = pref.getString("Age","");
                hobby = pref.getString("Hobby","");
                if (validator()) {
                    view_account.setText(account);
                    edit_nickname.setText(nickname);
                    spinner_gen.setSelection(adapter_gen.getPosition(gender));
                    spinner_ages.setSelection(adapter_ages.getPosition(age));
                    edit_hobby.setText(hobby);
                }
                else
                    button_act.setText(R.string.prompt_submit);
            }
            catch (Exception e) {
                Log.e(TAG, "onCreate: ERR", e);
                button_act.setText(R.string.prompt_submit);
            }
        }

        button_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = edit_nickname.getText().toString();
                gender = spinner_gen.getItemAtPosition(spinner_gen.getSelectedItemPosition()).toString();
                age = spinner_ages.getItemAtPosition(spinner_ages.getSelectedItemPosition()).toString();
                hobby = edit_hobby.getText().toString();
                if (validator()) {
                    SharedPreferences.Editor editor = getSharedPreferences(account, MODE_PRIVATE).edit();
                    editor.putString("Nickname", nickname);
                    editor.putString("Gender", gender);
                    editor.putString("Age", age);
                    editor.putString("Hobby", hobby);
                    editor.apply();
                }
                else {
                    Toast.makeText(getBaseContext(), "CHK Table", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button_bak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validator() {
        return !nickname.equals("") && !gender.equals("") && !age.equals("") && !hobby.equals("");
    }
}