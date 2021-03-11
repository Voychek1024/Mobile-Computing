package com.shu_mc_03.word_town;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText text_username = findViewById(R.id.input_username);
        TextInputEditText text_password = findViewById(R.id.input_password);

        MaterialButton button_login = findViewById(R.id.prompt_login_intent);
        MaterialButton button_register = findViewById(R.id.prompt_register_intent);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = text_username.getText().toString();
                String pwd = text_password.getText().toString();
                if (acc_validator(acc, pwd)) {
                    Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("username", acc);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = text_username.getText().toString();
                String pwd = text_password.getText().toString();
                if (register(acc, pwd)) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("username", acc);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean acc_validator(String ac, String pw) {
        String pw_check_idx = "Pwd_" + ac;
        SharedPreferences pref = getSharedPreferences("Account_Storage", Context.MODE_PRIVATE);
        String pw_check = pref.getString(pw_check_idx, "");
        if (pw_check.equals(""))
            return false;
        else return pw_check.equals(getMD5(pw));
    }

    private boolean reg_validator(String ac, String pw) {
        return !ac.equals("") && !pw.equals("");
    }

    private boolean register(String ac, String pw) {
        if (reg_validator(ac, pw)) {
            SharedPreferences.Editor editor = getSharedPreferences("Account_Storage", Context.MODE_PRIVATE).edit();
            SharedPreferences pref = getSharedPreferences("Account_Storage", Context.MODE_PRIVATE);
            String pwd_stamp = "Pwd_" + ac;
            String pw_check = pref.getString(pwd_stamp, "");
            if (!pw_check.equals("")) {
                Toast.makeText(getBaseContext(), "CHK Register Table", Toast.LENGTH_SHORT).show();
                return false;
            }
            String pw_write = getMD5(pw);
            editor.putString(pwd_stamp, pw_write);
            editor.apply();
            Toast.makeText(getBaseContext(), "Register Successful", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getBaseContext(), "CHK Register Table", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}