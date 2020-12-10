package com.example.a18121772_lijiaqi_week09;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_WAREHOUSE = "create table storage(" +
            "id INTEGER primary key autoincrement, " +
            "name text, " +
            "specification text, " +
            "price real, " +
            "quantity INTEGER)";
    private Context mContext;
    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WAREHOUSE);
        Toast.makeText(mContext, "CREATE SUCCESS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists storage");
        onCreate(db);
        Toast.makeText(mContext, "UPDATE SUCCESS", Toast.LENGTH_SHORT).show();
    }
}
