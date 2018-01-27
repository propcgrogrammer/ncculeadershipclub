package com.example.tingkuanlin.leadership;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private int[] image = {
            R.drawable.club,R.drawable.zen,R.drawable.calendar,
            R.drawable.alarm,R.drawable.countdown,R.drawable.facebook,
            R.drawable.note,R.drawable.about
    };
    private String[] imgText = {
            "社課時間", "禪定行程", "日曆", "禪定提醒", "禪定倒數器", "粉絲專頁",
            "記事本","關於領袖社"
    };

    private GridView gridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", image[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.grid_items, new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text});
        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setNumColumns(3);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tv = (TextView) view.findViewById(R.id.text);

                if(i == 0){

                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("此功能將於學期初開放")
                            .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
                else if(i == 1){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ZenScheduleActivity.class);
                    startActivity(intent);

                }
                else if(i == 2){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, CalendarActivity.class);
                    startActivity(intent);

                }
                else if(i == 3){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, RemindZenActivity.class);
                    startActivity(intent);

                }
                else if(i == 4){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, CountDownActivity.class);
                    startActivity(intent);

                }
                else if(i == 5){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, WebViewActivity.class);
                    startActivity(intent);

                }
                else if(i == 6){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, NoteActivity.class);
                    startActivity(intent);
                }
                else if(i == 7){

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                }


            }
        });


    }
}
