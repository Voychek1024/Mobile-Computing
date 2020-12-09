package com.example.a18121772_lijiaqi_week09.tasks.Task01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a18121772_lijiaqi_week09.Data_Form;
import com.example.a18121772_lijiaqi_week09.R;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Task01_Fragment extends Fragment {
    public static final String DATE_FORMAT = "yyyy_mm_dd_HH_mm_ss";
    private static final String TAG = "LOG";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Task01_ViewModel task01ViewModel = new ViewModelProvider(this).get(Task01_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_task01, container, false);
        final TextView textView = root.findViewById(R.id.text_task01);
        task01ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button_login = (Button) root.findViewById(R.id.button_login);
        Button button_register = (Button) root.findViewById(R.id.button_register);
        EditText editText_account = (EditText) root.findViewById(R.id.edit_account);
        EditText editText_password = (EditText) root.findViewById(R.id.edit_password);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = editText_account.getText().toString();
                String pwd = editText_password.getText().toString();
                // TODO: Validator() -> read from sharedPreferences
                if (acc_validator(acc, pwd)) {
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), Data_Form.class);
                    intent.putExtra("Status","1");
                    intent.putExtra("current_acc",acc);
                    v.getContext().startActivity(intent);
                }
                else
                    Toast.makeText(getContext(), "CHK Login Table", Toast.LENGTH_SHORT).show();
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = editText_account.getText().toString();
                String pwd = editText_password.getText().toString();
                if (register(acc, pwd)) {
                    Intent intent = new Intent(v.getContext(), Data_Form.class);
                    intent.putExtra("Status","0");
                    intent.putExtra("current_acc",acc);
                    v.getContext().startActivity(intent);
                }
            }
        });

        return root;
    }

    private boolean acc_validator(String ac, String pw) {
        String pw_check_idx = "Pwd_" + ac;
        SharedPreferences pref = getContext().getSharedPreferences("Account_Storage", Context.MODE_PRIVATE);
        String pw_check = pref.getString(pw_check_idx, "");
        if (pw_check.equals(""))
            return false;
        else return pw_check.equals(getMD5(pw));
    }

    private boolean reg_validator(String ac, String pw) {
        return !ac.equals("") && !pw.equals("");
    }

    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        }
        catch (Exception e) {
            Log.e(TAG, "getMD5: ERR", e);
            return "EOF";
        }
    }

    private boolean register(String ac, String pw) {
        if (reg_validator(ac, pw)) {
            SharedPreferences.Editor editor = getContext().getSharedPreferences("Account_Storage", Context.MODE_PRIVATE).edit();
            SharedPreferences pref = getContext().getSharedPreferences("Account_Storage", Context.MODE_PRIVATE);
            String pwd_stamp = "Pwd_" + ac;
            String pw_check = pref.getString(pwd_stamp, "");
            if (!pw_check.equals("")) {
                Toast.makeText(getContext(), "CHK Register Table", Toast.LENGTH_SHORT).show();
                return false;
            }
            String pw_write = getMD5(pw);
            editor.putString(pwd_stamp, pw_write);
            editor.apply();
            Toast.makeText(getContext(), "Register Successful", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getContext(), "CHK Register Table", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}