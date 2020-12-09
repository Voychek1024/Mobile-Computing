package com.example.a18121772_lijiaqi_week09.tasks.Task02;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a18121772_lijiaqi_week09.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Task02_Fragment extends Fragment {

    private static final String TAG = "LOG";
    private String assemble = "";
    private Task02_ViewModel task02ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        task02ViewModel =
                new ViewModelProvider(this).get(Task02_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_task02, container, false);
        final TextView textView = root.findViewById(R.id.text_task02);
        task02ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        TextView view = (TextView) root.findViewById(R.id.text_result);

        Button button_act = (Button) root.findViewById(R.id.button_pull);
        button_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = getContext().openFileInput("18121772.xml");
                    InputStreamReader reader = new InputStreamReader(fin);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        builder.append(line);
                    }
                    reader.close();
                    parseXML_Pull(builder.toString());
                    Log.d(TAG, "onClick() returned: " + assemble);
                    view.setText(assemble);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    private void parseXML_Pull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int event_type = xmlPullParser.getEventType();
            String nickname = "";
            String gender = "";
            String age = "";
            String hobby = "";
            while (event_type != XmlPullParser.END_DOCUMENT) {
                String node = xmlPullParser.getName();
                switch (event_type) {
                    case XmlPullParser.START_TAG: {
                        if ("Nickname".equals(node)) {
                            nickname = xmlPullParser.nextText();
                        }
                        else if ("Gender".equals(node)) {
                            gender = xmlPullParser.nextText();
                        }
                        else if ("Age".equals(node)) {
                            age = xmlPullParser.nextText();
                        }
                        else if ("Hobby".equals(node)) {
                            hobby = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("map".equals(node)) {
                            assemble = "Nickname: " + nickname + "\nGender: " +
                                    gender + "\nAge: " + age + "\nHobby: " + hobby;
                        }
                        break;
                    }
                    default:
                        break;
                }
                event_type = xmlPullParser.next();
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }
    }
}