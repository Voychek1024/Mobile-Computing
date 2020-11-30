package com.example.a18121772_lijiaqi_week02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter_1 = ArrayAdapter.createFromResource(this,
                R.array.gender_choose, R.layout.support_simple_spinner_dropdown_item);
        adapter_1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_1);

        Spinner spinner_3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter_2 = ArrayAdapter.createFromResource(this,
                R.array.spec_choose, R.layout.support_simple_spinner_dropdown_item);
        adapter_2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_3.setAdapter(adapter_2);
    }
}