package com.example.tingkuanlin.leadership.DBHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tingkuanlin on 2018/1/8.
 */

public class ZenDBHelper extends SQLiteOpenHelper {


    public ZenDBHelper(Context context) {
        super(context, "zen", null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table zen(id integer PRIMARY KEY autoincrement,type integer,start_date char,start_time char,end_date char,end_time char,notify integer , remind_date char , remind_time char)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }


}
