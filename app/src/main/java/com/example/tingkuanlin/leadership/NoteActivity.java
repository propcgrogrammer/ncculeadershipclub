package com.example.tingkuanlin.leadership;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.tingkuanlin.leadership.Controllers.NoteAdapter;
import com.example.tingkuanlin.leadership.DBHelpers.NoteDBOperators;
import com.example.tingkuanlin.leadership.NotePad.Notepad;

import java.util.ArrayList;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class NoteActivity extends AppCompatActivity {


    private ListView listview;
    LayoutInflater inflater;
    ArrayList<Notepad> array;
    NoteDBOperators noteDBOperators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        listview=(ListView) findViewById(R.id.note_listview);
        inflater=getLayoutInflater();

        noteDBOperators=new NoteDBOperators(this);
        array=noteDBOperators.getArray();
        NoteAdapter adapter=new NoteAdapter(inflater,array);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(getApplicationContext(),NotePadActivity.class);
                intent.putExtra("ids",array.get(position).getId() );
                startActivity(intent);
                NoteActivity.this.finish();
            }
        });
		/*
		 * 长按后判断是否删除当前记事
		 */
        listview.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                //AlertDialog判断是否删除记事
                new AlertDialog.Builder(NoteActivity.this)
                        .setMessage("是否刪除該筆記？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                noteDBOperators.toDelete(array.get(position).getId());
                                array=noteDBOperators.getArray();
                                NoteAdapter adapter=new NoteAdapter(inflater,array);
                                listview.setAdapter(adapter);
                            }
                        })
                        .create().show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent=new Intent(getApplicationContext(),NotePadActivity.class);
                startActivity(intent);

                break;
            case R.id.item2:

                break;
            default:
                break;
        }
        return true;


    }


}
