package com.shu_mc_03.illusion.ui.studio;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;

import com.shu_mc_03.illusion.R;

public class StudioIntentActivity extends AppCompatActivity {

    private static final String TAG = "LOG_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio_intent);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Uri uri = getIntent().getParcelableExtra("imageUri");
        Log.d(TAG, "onCreate() returned: " + uri);
        ImageView imageView = (ImageView) findViewById(R.id.stu_img);
        imageView.setImageURI(Uri.parse(uri.toString()));
    }
}