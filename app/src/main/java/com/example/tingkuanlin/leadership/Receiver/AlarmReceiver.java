package com.example.tingkuanlin.leadership.Receiver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.tingkuanlin.leadership.RingToneActivity;
import com.example.tingkuanlin.leadership.ZenSettingActivity;

import java.util.Timer;
import java.util.TimerTask;

import static android.media.RingtoneManager.TYPE_ALARM;

/**
 * Created by tingkuanlin on 2018/1/9.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

//        // 取得預設的鬧鐘鈴聲URI路徑
//        Uri alarmUri= RingtoneManager.getDefaultUri(TYPE_ALARM);
//        // 取得鈴聲
//        final Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        // 播放鈴聲
//        ringtone.play();
//
//        // 定義一個計時器程序來停止播放鈴聲
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                ringtone.stop();
//            }
//        };
//        Timer timer = new Timer();
//        // 5秒後停止鈴聲
//        timer.schedule(task, 8000);

        Intent intent1 = new Intent(context, RingToneActivity.class);
        Bundle bundle = intent.getExtras();
        intent1.putExtra("start_date_time",bundle.getString("start_date_time"));
        context.startActivity(intent1);
    }
}
