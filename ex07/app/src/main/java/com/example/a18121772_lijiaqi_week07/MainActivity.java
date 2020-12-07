package com.example.a18121772_lijiaqi_week07;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a18121772_lijiaqi_week07.databinding.ActivityMainBinding;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Err Message";
    private double value_one = Double.NaN;
    private double value_two;

    private boolean dot_once = true;

    private static final char PLUS = '+';
    private static final char SUBTRACT = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    private char CURRENT_ACTION;
    DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    private ActivityMainBinding binding;

    private void compute()  {
        if (!Double.isNaN(value_one)) {
            value_two = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if (CURRENT_ACTION == PLUS) {
                value_one = this.value_one + value_two;
            }
            else if (CURRENT_ACTION == SUBTRACT) {
                value_one = this.value_one - value_two;
            }
            else if (CURRENT_ACTION == MULTIPLY) {
                value_one = this.value_one * value_two;
            }
            else if (CURRENT_ACTION == DIVIDE) {
                value_one = this.value_one / value_two;
            }
        }
        else {
            try {
                value_one = Double.parseDouble(binding.editText.getText().toString());
            }
            catch (Exception e) {
                Log.e(TAG, "compute: ", e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button0.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });
        binding.buttonP.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                compute();
                CURRENT_ACTION = PLUS;
                binding.infoTextView.setText(decimalFormat.format(value_one) + " + ");
                binding.editText.setText(null);
                dot_once = true;
            }
        });
        binding.buttonS.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                compute();
                CURRENT_ACTION = SUBTRACT;
                binding.infoTextView.setText(decimalFormat.format(value_one) + " - ");
                binding.editText.setText(null);
                dot_once = true;
            }
        });
        binding.buttonM.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                compute();
                CURRENT_ACTION = MULTIPLY;
                binding.infoTextView.setText(decimalFormat.format(value_one) + " * ");
                binding.editText.setText(null);
                dot_once = true;
            }
        });
        binding.buttonD.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                compute();
                CURRENT_ACTION = DIVIDE;
                binding.infoTextView.setText(decimalFormat.format(value_one) + " / ");
                binding.editText.setText(null);
                dot_once = true;
            }
        });
        binding.buttonE.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                compute();
                binding.infoTextView.setText(binding.infoTextView.getText().toString() + decimalFormat.format(value_two) + " = " + decimalFormat.format(value_one));
                value_one = Double.NaN;
                CURRENT_ACTION = '0';
                dot_once = true;
            }
        });
        binding.buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.infoTextView.setText(null);
                binding.editText.setText(null);
                value_one = Double.NaN;
                dot_once = true;
            }
        });
        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (dot_once) {
                    if (binding.editText.getText().toString().equals("")) {
                        binding.editText.setText("0.");
                    }
                    else {
                        binding.editText.setText(binding.editText.getText() + ".");
                    }
                    dot_once = false;
                }
            }
        });
        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (binding.editText.getText().toString().charAt(0) == '-') {
                    binding.editText.setText(binding.editText.getText().toString().substring(1));
                }
                else {
                    binding.editText.setText('-' + binding.editText.getText().toString());
                }
            }
        });
    }
}