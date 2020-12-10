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

        Spinner spinner_gen = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter_gen = ArrayAdapter.createFromResource(this,
                R.array.gender_choose, R.layout.support_simple_spinner_dropdown_item);
        adapter_gen.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_gen.setAdapter(adapter_gen);

        Spinner spinner_pro = (Spinner) findViewById(R.id.spinner_profession);
        ArrayAdapter<CharSequence> adapter_pro = ArrayAdapter.createFromResource(this,
                R.array.spec_choose, R.layout.support_simple_spinner_dropdown_item);
        adapter_pro.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_pro.setAdapter(adapter_pro);
        adapter_gen.notifyDataSetChanged();
    }
}