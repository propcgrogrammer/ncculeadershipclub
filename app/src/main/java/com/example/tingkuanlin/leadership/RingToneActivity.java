package com.example.tingkuanlin.leadership;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.media.RingtoneManager.TYPE_ALARM;

/**
 * Created by tingkuanlin on 2018/1/10.
 */

public class RingToneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtone);

        // 取得預設的鬧鐘鈴聲URI路徑
        Uri alarmUri= RingtoneManager.getDefaultUri(TYPE_ALARM);
        // 取得鈴聲
        final Ringtone ringtone = RingtoneManager.getRingtone(this, alarmUri);
        // 播放鈴聲
        ringtone.play();

        Intent intent = getIntent();
        String start_date_time =intent.getExtras().getString("start_date_time");
        new AlertDialog.Builder(RingToneActivity.this)
                .setTitle("禪定行程提醒")
                .setMessage("您於 "+start_date_time+" 將有禪定計畫")
                .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       ringtone.stop();
                    }
                })
                .create().show();

    }
}
