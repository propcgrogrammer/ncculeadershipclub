package com.example.tingkuanlin.leadership;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tingkuanlin.leadership.Controllers.NoteAdapter;
import com.example.tingkuanlin.leadership.Controllers.ZenAdapter;
import com.example.tingkuanlin.leadership.DBHelpers.NoteDBOperators;
import com.example.tingkuanlin.leadership.DBHelpers.ZenDBHelper;
import com.example.tingkuanlin.leadership.DBHelpers.ZenDBOperators;
import com.example.tingkuanlin.leadership.NotePad.Notepad;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class ZenScheduleActivity extends AppCompatActivity {


    private ListView zen_schedule_list_view = null;

    private List<Map<String, Object>> items = null;
    private Map<String, Object> item = null;

    private View view = null;


    LayoutInflater inflater;
    ArrayList<Zen> array;
    ZenDBOperators zenDBOperators = null;


    private String[] tittle = {"晨間禪","午間禪","晚間禪"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen_schedule);

        zen_schedule_list_view = (ListView) findViewById(R.id.zen_list_view);

        inflater=getLayoutInflater();

        zenDBOperators=new ZenDBOperators(this);
        array=zenDBOperators.getArray();
        ZenAdapter adapter=new ZenAdapter(inflater,array);
        zen_schedule_list_view.setAdapter(adapter);

        zen_schedule_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                int id = array.get(position).getId();
                zenDBOperators = new ZenDBOperators(ZenScheduleActivity.this);
                Zen zen= zenDBOperators.getZen(id);
                String start_date = zen.getStart_date();
                String start_time = zen.getStart_time();
                StringTokenizer stringTokenizer = new StringTokenizer(start_date, "-");
                int year = Integer.parseInt(stringTokenizer.nextToken());
                int month = Integer.parseInt(stringTokenizer.nextToken());
                int day = Integer.parseInt(stringTokenizer.nextToken());

                stringTokenizer = new StringTokenizer(start_time, ":");
                int hour = Integer.parseInt(stringTokenizer.nextToken());
                int min = Integer.parseInt(stringTokenizer.nextToken());
                int sec = Integer.parseInt(stringTokenizer.nextToken());
                Calendar cal = Calendar.getInstance();
                cal.set(year, month - 1, day, hour, min, sec);

                Calendar now = Calendar.getInstance();

                if(cal.before(now)){

                    new android.app.AlertDialog.Builder(ZenScheduleActivity.this)
                            .setMessage("時間已過，僅可檢視！！")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }
                else {
                    Intent intent = new Intent(getApplicationContext(), ZenSettingActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }
        });

        zen_schedule_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int position, long l) {

                // TODO Auto-generated method stub
                //AlertDialog判断是否删除记事
                new android.app.AlertDialog.Builder(ZenScheduleActivity.this)
                        .setMessage("是否刪除該禪定行程？")
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
                                zenDBOperators.toDelete(array.get(position).getId());
                                array=zenDBOperators.getArray();
                                ZenAdapter adapter=new ZenAdapter(inflater,array);
                                zen_schedule_list_view.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.zen_list_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.zen_add:
                Intent intent = new Intent();
                intent.setClass(ZenScheduleActivity.this, ZenSettingActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        return true;


    }


}
