package com.example.a18121772_lijiaqi_week03.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.a18121772_lijiaqi_week03.R;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class T02_Fragment extends Fragment {

    private T02_ViewModel t02_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        t02_ViewModel =
                new ViewModelProvider(this).get(T02_ViewModel.class);
        View root = inflater.inflate(R.layout.frag_task_02, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        t02_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button button = root.findViewById(R.id.button2);
        button.setOnClickListener(this::onClick);
        return root;
    }

    public void onClick(View v) {
        EditText e_1 = v.getRootView().findViewById(R.id.editTextNumberSigned);
        EditText e_2 = v.getRootView().findViewById(R.id.editTextNumberSigned2);
        TextView result = v.getRootView().findViewById(R.id.textView3);
        if (v.getId() == R.id.button2) {
            try {
                int a_1 = Integer.parseInt(e_1.getText().toString());
                int a_2 = Integer.parseInt(e_2.getText().toString());
                int res;
                res = a_1 + a_2;
                Log.d(TAG, "onClick() returned: " + e_1.getText() + ", " + e_2.getText());
                Log.d(TAG, "onClick() returned: " + a_1 + ", " + a_2 + ", " + res);
                result.setText(String.valueOf(res));
            }
            catch (Exception e) {
                Log.i(TAG, "onClick: Not adequate inputs");
                result.setText("CHK");
            }
        }
    }
}