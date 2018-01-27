package com.example.tingkuanlin.leadership.DBHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tingkuanlin.leadership.NotePad.Notepad;

import java.util.ArrayList;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class NoteDBOperators {

    Context context;
    NoteDBHelper noteDBHelper;
    SQLiteDatabase myDatabase;

    public NoteDBOperators(Context context){
        this.context=context;
        noteDBHelper=new NoteDBHelper(context);
    }

    public ArrayList<Notepad> getArray(){
        ArrayList<Notepad> array=new ArrayList<Notepad>();
        ArrayList<Notepad> array1=new ArrayList<Notepad>();
        myDatabase=noteDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select ids,title,times from mybook" , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("ids"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String times=cursor.getString(cursor.getColumnIndex("times"));
            Notepad note=new Notepad(id, title, times);
            array.add(note);
            cursor.moveToNext();
        }
        myDatabase.close();
        for (int i = array.size(); i >0; i--) {
            array1.add(array.get(i-1));
        }
        return array1;
    }


    public Notepad getTiandCon(int id){
        myDatabase=noteDBHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select title,content from mybook where ids='"+id+"'" , null);
        cursor.moveToFirst();
        String title=cursor.getString(cursor.getColumnIndex("title"));
        String content=cursor.getString(cursor.getColumnIndex("content"));
        Notepad cun=new Notepad(title,content);
        myDatabase.close();
        return cun;
    }

    public void toUpdate(Notepad note){
        myDatabase=noteDBHelper.getWritableDatabase();
        myDatabase.execSQL("update mybook set title='"+ note.getTitle()+"',times='"+note.getTimes()+"',content='"+note.getContent() +"' where ids='"+ note.getId()+"'");
        myDatabase.close();
    }

    public void toInsert(Notepad note){
        myDatabase=noteDBHelper.getWritableDatabase();
        myDatabase.execSQL("insert into mybook(title,content,times)values('"+ note.getTitle()+"','"+note.getContent()+"','"+note.getTimes()+"')");
        myDatabase.close();
    }

    public void toDelete(int ids){
        myDatabase=noteDBHelper.getWritableDatabase();
        myDatabase.execSQL("delete from mybook where ids="+ids+"");
        myDatabase.close();
    }

}
