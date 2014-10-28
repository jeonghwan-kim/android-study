package com.example.jeonghwan.myapplication4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jeonghwan on 14. 10. 28..
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "db.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("tag", "onCreate() DBHelper.java");

        String table = "CREATE TABLE cars " +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, car_name TEXT NOT NULL)";
        Log.d("tab", table);

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        Log.d("tag", "onUpgrade() DBHelper.java");

        db.execSQL("DROP TABLE IF EXISTS cars");
        onCreate(db);
    }
}
