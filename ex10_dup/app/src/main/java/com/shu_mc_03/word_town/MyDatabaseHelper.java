package com.shu_mc_03.word_town;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLITE DEBUG: ";
    private final Context mContext;

    public static String getDBLocation(Context c, @Nullable String filename) {
        if (filename == null)
            return Environment.getDataDirectory() +
                    File.separator + "data" +
                    File.separator + c.getPackageName() +
                    File.separator + "databases";
        else
            return Environment.getDataDirectory() +
                    File.separator + "data" +
                    File.separator + c.getPackageName() +
                    File.separator + "databases" +
                    File.separator + "Words.db";
    }

    public static void copyDatabase(Context c) {
        File db_dir = new File(getDBLocation(c, null));
        if (!db_dir.exists())
            db_dir.mkdirs();
        File db = new File(getDBLocation(c, "Words.db"));
        if (!db.exists())
        {
            // copy Words.db from Assets to Path
            AssetManager manager = c.getAssets();
            try {
                InputStream in = manager.open("Words.db");
                OutputStream out = new FileOutputStream(getDBLocation(c, "Words.db"));
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
            }
            catch (IOException e) {
                Log.e(TAG, "SQLITE WRITE ERR", e);
            }
        }
    }

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        copyDatabase(context);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext, "Init Database", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
