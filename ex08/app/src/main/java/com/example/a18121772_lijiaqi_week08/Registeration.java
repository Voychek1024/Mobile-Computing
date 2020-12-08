package com.example.a18121772_lijiaqi_week08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class Registeration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ArrayAdapter<CharSequence> adapter_gender =
                ArrayAdapter.createFromResource(this, R.array.gender_choice,
                        R.layout.support_simple_spinner_dropdown_item);
        adapter_gender.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        spinner_gender.setAdapter(adapter_gender);
        ArrayAdapter<CharSequence> adapter_profession =
                ArrayAdapter.createFromResource(this, R.array.profession_choice,
                        R.layout.support_simple_spinner_dropdown_item);
        adapter_profession.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner_profession = (Spinner) findViewById(R.id.spinner_profession);
        spinner_profession.setAdapter(adapter_profession);
        // TODO: Birthday spinner implementation -> Changed to MD:picker

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
                // TODO: save txt to local storage
                finish();
            }
        });
    }
}