package com.example.tingkuanlin.leadership.DBHelpers;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tingkuanlin.leadership.Alarm.ZenAlarm;
import com.example.tingkuanlin.leadership.NotePad.Notepad;
import com.example.tingkuanlin.leadership.Zen.RemindZen;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tingkuanlin on 2018/1/8.
 */

public class ZenDBOperators  {

    Context context;
    ZenDBHelper zenDBHelper;
    SQLiteDatabase myDatabase;

    public ZenDBOperators(Context context){
        this.context=context;
        zenDBHelper=new ZenDBHelper(context);
    }

//    public ArrayList<Zen> testArray(){
//
//        ArrayList<Zen> array=new ArrayList<Zen>();
//        Zen zen1 = new Zen(1,2,"2018-01-03","11:56:00","2018-01-06","23:45:00",2);
//        Zen zen2 = new Zen(2,1,"2018-02-13","02:56:00","2018-11-27","07:56:00",2);
//        array.add(zen1);
//        array.add(zen2);
//
//        return array;
//
//    }


    public ArrayList<Zen> getArray(){
        ArrayList<Zen> array=new ArrayList<Zen>();
        ArrayList<Zen> array1=new ArrayList<Zen>();
        myDatabase=zenDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select * from zen" , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            String start_date=cursor.getString(cursor.getColumnIndex("start_date"));
            String start_time=cursor.getString(cursor.getColumnIndex("start_time"));
            String end_date=cursor.getString(cursor.getColumnIndex("end_date"));
            String end_time=cursor.getString(cursor.getColumnIndex("end_time"));
            int notify = cursor.getInt(cursor.getColumnIndex("notify"));
            String remind_date=cursor.getString(cursor.getColumnIndex("remind_date"));
            String remind_time=cursor.getString(cursor.getColumnIndex("remind_time"));


            Zen zen = new Zen(id,type,start_date,start_time,end_date,end_time,notify,remind_date,remind_time);
            array.add(zen);
            cursor.moveToNext();
        }
        myDatabase.close();
        for (int i = array.size(); i >0; i--) {
            array1.add(array.get(i-1));
        }
        return array1;
    }
    public Zen getZen(int id){
        myDatabase=zenDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select * from zen where id='"+id+"'" , null);
        cursor.moveToFirst();

        int type = cursor.getInt(cursor.getColumnIndex("type"));
        String start_date=cursor.getString(cursor.getColumnIndex("start_date"));
        String start_time=cursor.getString(cursor.getColumnIndex("start_time"));
        String end_date=cursor.getString(cursor.getColumnIndex("end_date"));
        String end_time=cursor.getString(cursor.getColumnIndex("end_time"));
        int notify = cursor.getInt(cursor.getColumnIndex("notify"));
        String remind_date=cursor.getString(cursor.getColumnIndex("remind_date"));
        String remind_time=cursor.getString(cursor.getColumnIndex("remind_time"));

        Zen zen = new Zen(type,start_date,start_time,end_date,end_time,notify,remind_date,remind_time);
        myDatabase.close();
        return zen;
    }
    public void toUpdate(Zen zen){
        myDatabase=zenDBHelper.getWritableDatabase();
        myDatabase.execSQL("update zen set type='"+ zen.getType()+"',start_date='"+zen.getStart_date()+"',start_time='"+zen.getStart_time()
                + "',end_date='"+zen.getEnd_date() + "',end_time='"+zen.getEnd_time() + "',notify='"+zen.getNotify()
                + "',remind_date='"+zen.getRemind_date() + "',remind_time='"+zen.getRemind_time()
                +"' where id='"+ zen.getId()+"'");
        myDatabase.close();
    }
    public void toInsert(Zen zen){
        myDatabase=zenDBHelper.getWritableDatabase();
        myDatabase.execSQL("insert into zen(type,start_date,start_time,end_date,end_time,notify,remind_date,remind_time)values('"+
                zen.getType()+"','"+zen.getStart_date()+"','"+zen.getStart_time()
                + "','"+zen.getEnd_date()+ "','"+zen.getEnd_time() + "','"+zen.getNotify()
                + "','"+zen.getRemind_date() + "','"+zen.getRemind_time()
                +"')");
        myDatabase.close();
    }

    public void toDelete(int id){
        myDatabase=zenDBHelper.getWritableDatabase();
        myDatabase.execSQL("delete from zen where id="+id+"");
        myDatabase.close();
    }



}
