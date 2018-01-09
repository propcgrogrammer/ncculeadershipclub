package com.example.tingkuanlin.leadership;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.tingkuanlin.leadership.DBHelpers.NoteDBHelper;
import com.example.tingkuanlin.leadership.DBHelpers.NoteDBOperators;
import com.example.tingkuanlin.leadership.NotePad.Notepad;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class NotePadActivity extends AppCompatActivity {

    EditText ed1,ed2;
    NoteDBOperators noteDBOperators;
    Notepad notepad;
    int ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的筆記");
        setContentView(R.layout.activity_note);
        ed1=(EditText) findViewById(R.id.editText1);
        ed2=(EditText) findViewById(R.id.editText2);
        noteDBOperators=new NoteDBOperators(this);

        Intent intent=this.getIntent();
        ids=intent.getIntExtra("ids", 0);
        if(ids!=0){
            notepad= noteDBOperators.getTiandCon(ids);
            ed1.setText(notepad.getTitle());
            ed2.setText(notepad.getContent());
        }
    }
    @Override
    public void onBackPressed() {
        //TODO Auto-generated method stub
        Intent intent=new Intent(NotePadActivity.this,NoteActivity.class);
        startActivity(intent);
        finish();
    }
    private void isSave(){
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日  HH:mm");
        Date curDate   =   new Date(System.currentTimeMillis());
        String times   =   formatter.format(curDate);
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();

        if(ids!=0){
            notepad=new Notepad(title,ids, content, times);
            noteDBOperators.toUpdate(notepad);
            Intent intent=new Intent(NotePadActivity.this,NoteActivity.class);
            startActivity(intent);
            finish();
        }

        else{
            notepad=new Notepad(title,content,times);
            noteDBOperators.toInsert(notepad);
            Intent intent=new Intent(NotePadActivity.this,NoteActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notepad_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "標題："+ed1.getText().toString()+"  \n內容："+ed2.getText().toString());
                startActivity(intent);
                break;
            case R.id.save:
                isSave();
                Intent intent1 = new Intent(NotePadActivity.this,NoteActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
        return false;
    }




}
