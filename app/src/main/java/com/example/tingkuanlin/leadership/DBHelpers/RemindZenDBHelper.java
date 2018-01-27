package com.example.tingkuanlin.leadership.DBHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tingkuanlin on 2018/1/9.
 */

public class RemindZenDBHelper extends SQLiteOpenHelper {

    public RemindZenDBHelper(Context context) {
        super(context, "remind", null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table remind(id integer PRIMARY KEY autoincrement, notify integer,remind_date char,remind_time char)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }


}
