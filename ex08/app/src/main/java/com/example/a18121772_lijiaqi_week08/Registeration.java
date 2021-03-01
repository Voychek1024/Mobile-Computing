package com.example.a18121772_lijiaqi_week08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class Registeration extends AppCompatActivity {

    private static final String TAG = "TEST LOG";
    private String name;
    private String gender;
    // For now date is String
    private String date;
    private String stu_num;
    private String profession;
    private String hobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        // Gender spinner
        ArrayAdapter<CharSequence> adapter_gender =
                ArrayAdapter.createFromResource(this, R.array.gender_choice,
                        R.layout.support_simple_spinner_dropdown_item);
        adapter_gender.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        spinner_gender.setAdapter(adapter_gender);

        // Profession spinner
        ArrayAdapter<CharSequence> adapter_profession =
                ArrayAdapter.createFromResource(this, R.array.profession_choice,
                        R.layout.support_simple_spinner_dropdown_item);
        adapter_profession.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_profession = (Spinner) findViewById(R.id.spinner_profession);
        spinner_profession.setAdapter(adapter_profession);

        // Age spinner
        ArrayList<String> ages = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1960; i--) {
            ages.add(Integer.toString(thisYear - i));
        }
        ArrayAdapter<String> adapter_ages = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ages);
        Spinner spinner_ages = (Spinner) findViewById(R.id.spinner_age);
        spinner_ages.setAdapter(adapter_ages);

        // Buttons binding
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        Button button_submit = (Button) findViewById(R.id.button_submit);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Validator()
                if (validator()) {
                    save(); // save txt to local storage
                    finish();
                }
            }
        });
    }

    // Sheet Validator
    private boolean validator() {
        EditText editText_name = (EditText) findViewById(R.id.editText_Name);
        EditText editText_stu_num = (EditText) findViewById(R.id.editText_stu_number);
        EditText editText_hobby = (EditText) findViewById(R.id.editText_hobby);
        name = editText_name.getText().toString();
        stu_num = editText_stu_num.getText().toString();
        hobby = editText_hobby.getText().toString();

        Spinner spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        gender = spinner_gender.getItemAtPosition(spinner_gender.getSelectedItemPosition()).toString();
        Spinner spinner_age = (Spinner) findViewById(R.id.spinner_age);
        date = spinner_age.getItemAtPosition(spinner_age.getSelectedItemPosition()).toString();
        Spinner spinner_profession = (Spinner) findViewById(R.id.spinner_profession);
        profession = spinner_profession.getItemAtPosition(spinner_profession.getSelectedItemPosition()).toString();

        return !name.equals("") && !stu_num.equals("") && spinner_gender.getSelectedItemPosition() != 0 && spinner_profession.getSelectedItemPosition() != 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MODIFIED");
    }

    // Java Stream_output
    private void save() {
        Log.i(TAG, "save: SUCCESSFUL JUDGEMENT");
        FileOutputStream stream = null;
        BufferedWriter writer = null;
        try {
            stream = openFileOutput("18121772_LiJiaQi.txt", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(stream));
            String out = "";
            out += "Name: " + name + "\nGender: " + gender + "\nAge: " + date +
                    "\nSTU_Number: " + stu_num + "\nProfession: " + profession +
                    "\nHobby: " + hobby + "\n\n";
            writer.write(out);
        }
        catch (IOException e) {
            Log.e(TAG, "save: ERR_WRITE", e);
        }
        finally {
            try {
                if (writer != null)
                    writer.close();
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                Log.e(TAG, "save: ERR_SHUT", e);
            }
        }
    }
}