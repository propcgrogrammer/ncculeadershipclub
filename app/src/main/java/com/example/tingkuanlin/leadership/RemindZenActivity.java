package com.example.tingkuanlin.leadership;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tingkuanlin.leadership.Controllers.RemindZenAdapter;
import com.example.tingkuanlin.leadership.Controllers.ZenAdapter;
import com.example.tingkuanlin.leadership.DBHelpers.RemindZenDBOperators;
import com.example.tingkuanlin.leadership.DBHelpers.ZenDBOperators;
import com.example.tingkuanlin.leadership.Zen.RemindZen;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tingkuanlin on 2018/1/9.
 */

public class RemindZenActivity extends AppCompatActivity {

    private ListView zen_remind_list_view = null;

    private View view = null;


    LayoutInflater inflater;
    ArrayList<Zen> array;
    ZenDBOperators zenDBOperators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen_remind);

        zen_remind_list_view = (ListView) findViewById(R.id.remind_list_view);

        inflater=getLayoutInflater();

        zenDBOperators=new ZenDBOperators(this);
        array=zenDBOperators.getArray();
        RemindZenAdapter adapter=new RemindZenAdapter(inflater,array);
        zen_remind_list_view.setAdapter(adapter);

    }

}
