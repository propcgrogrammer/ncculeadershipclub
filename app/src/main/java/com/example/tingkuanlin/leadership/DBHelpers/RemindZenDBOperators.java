package com.example.tingkuanlin.leadership.DBHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tingkuanlin.leadership.NotePad.Notepad;
import com.example.tingkuanlin.leadership.Zen.RemindZen;

import java.util.ArrayList;

/**
 * Created by tingkuanlin on 2018/1/9.
 */

public class RemindZenDBOperators  {

    Context context;
    RemindZenDBHelper remindZenDBHelper;
    SQLiteDatabase myDatabase;

    public RemindZenDBOperators(Context context){
        this.context=context;
        remindZenDBHelper=new RemindZenDBHelper(context);
    }
    public ArrayList<RemindZen> getRemindArray(){

        ArrayList<RemindZen> array=new ArrayList<RemindZen>();
        ArrayList<RemindZen> array1=new ArrayList<RemindZen>();
        myDatabase=remindZenDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select * from remind where notify = 1" , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            int notify=cursor.getInt(cursor.getColumnIndex("notify"));
            String remind_date=cursor.getString(cursor.getColumnIndex("remind_date"));
            String remind_time=cursor.getString(cursor.getColumnIndex("remind_time"));

            RemindZen remindZen=new RemindZen(id, notify, remind_date, remind_time);
            array.add(remindZen);
            cursor.moveToNext();
        }
        myDatabase.close();
        for (int i = array.size(); i >0; i--) {
            array1.add(array.get(i-1));
        }
        return array1;

    }
    public ArrayList<RemindZen> getArray(){
        ArrayList<RemindZen> array=new ArrayList<RemindZen>();
        ArrayList<RemindZen> array1=new ArrayList<RemindZen>();
        myDatabase=remindZenDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select * from remind" , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            int notify=cursor.getInt(cursor.getColumnIndex("notify"));
            String remind_date=cursor.getString(cursor.getColumnIndex("remind_date"));
            String remind_time=cursor.getString(cursor.getColumnIndex("remind_time"));
            RemindZen remindZen=new RemindZen(id, notify, remind_date, remind_time);
            array.add(remindZen);
            cursor.moveToNext();
        }
        myDatabase.close();
        for (int i = array.size(); i >0; i--) {
            array1.add(array.get(i-1));
        }
        return array1;
    }
    public RemindZen getRemindZen(int id){
        myDatabase=remindZenDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select * from remind where id='"+id+"'" , null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            int notify = cursor.getInt(cursor.getColumnIndex("notify"));
            String remind_date = cursor.getString(cursor.getColumnIndex("remind_date"));
            String remind_time = cursor.getString(cursor.getColumnIndex("remind_time"));
            RemindZen remindZen = new RemindZen(notify, remind_date, remind_time);
            myDatabase.close();
            return remindZen;
        }
        return null;
    }
    public void toUpdate(RemindZen remindZen){
        myDatabase=remindZenDBHelper.getWritableDatabase();
        String sql = "update remind set notify='"+ remindZen.getNotify()+"',remind_date='"+remindZen.getRemind_date()+"',remind_time='"+remindZen.getRemind_time() +"' where id='"+ remindZen.getId()+"'";
        myDatabase.execSQL(sql);

        Log.e("update => ",sql);
        myDatabase.close();
    }

    public void toInsert(RemindZen remindZen){
        myDatabase=remindZenDBHelper.getWritableDatabase();
        String sql = "insert into remind(notify,remind_date,remind_time)values('"+ remindZen.getNotify()+"','"+remindZen.getRemind_date()+"','"+remindZen.getRemind_time()+"')";
        myDatabase.execSQL(sql);

        Log.e("insert => ",sql);
        myDatabase.close();
    }

    public void toDelete(int id){
        myDatabase=remindZenDBHelper.getWritableDatabase();
        myDatabase.execSQL("delete from remind where id="+id+"");
        myDatabase.close();
    }


}
