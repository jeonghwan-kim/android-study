package com.example.jeonghwan.myapplication4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by jeonghwan on 14. 10. 28..
 */
public class DBHandler {
    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase(); // 쓰기 가능한 디비
    }

    public static DBHandler open(Context ctx) throws SQLException {
        DBHandler handler = new DBHandler(ctx);

        return handler;
    }

    public void close() {
        helper.close();
    }

    public long insert(String car_name) {
        ContentValues values = new ContentValues(); // 맵 계열의 클래스
        values.put("car_name", car_name);

        return db.insert("cars", null, values);
    }

//    public Cursor select(int id) throws SQLException {
    public Cursor select(int id) {
//        Cursor cursor = db.query(true, "cars", new String[] { "_id", "car_name" }, "_id=" + id, null, null, null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM cars where _id=?", new String[] { String.valueOf(id) });

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor selectAll() {
//        Cursor cursor = db.query(true, "cars",
//                new String[] {"_id", "car_name"},
//                null,
//                null, null, null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM cars", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
