package com.example.tingkuanlin.leadership.Receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tingkuanlin.leadership.DBHelpers.ZenDBHelper;
import com.example.tingkuanlin.leadership.DBHelpers.ZenDBOperators;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by tingkuanlin on 2018/1/10.
 */

public class BootReceiver extends BroadcastReceiver {

    private ZenDBOperators zenDBOperators = null;
    private ZenDBHelper zenDBHelper = null;
    private ArrayList<Zen> array = null;
    private ArrayList<Zen> arrayList = new ArrayList<Zen>();

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            Log.e("boot complete","receive intent");

            //Intent it = new Intent(context, MainActivity.class);
            //context.startActivity(it);

            zenDBOperators = new ZenDBOperators(context);
            array = zenDBOperators.getArray();

            for (Zen zen : array) {

                int notify = zen.getNotify();
                if (notify == 1) {
                    String date = zen.getRemind_date();
                    StringTokenizer stringTokenizer = new StringTokenizer(date, "-");
                    int year = Integer.parseInt(stringTokenizer.nextToken());
                    int month = Integer.parseInt(stringTokenizer.nextToken());
                    int day = Integer.parseInt(stringTokenizer.nextToken());
                    String time = zen.getRemind_time();
                    stringTokenizer = new StringTokenizer(time, ":");
                    int hour = Integer.parseInt(stringTokenizer.nextToken());
                    int min = Integer.parseInt(stringTokenizer.nextToken());
                    int sec = Integer.parseInt(stringTokenizer.nextToken());

                    Calendar now = Calendar.getInstance();

                    String dat = year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec;
                    Log.e("got date",dat);

                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month - 1, day, hour, min, sec);

                    if(cal.after(now)) {
                        Intent intent1 = new Intent(context, AlarmReceiver.class);

                        intent1.putExtra("start_date_time", zen.getStart_date_time());

                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        Random rand = new Random(System.currentTimeMillis());
                        int id = rand.nextInt(Integer.MAX_VALUE);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent1, PendingIntent.FLAG_ONE_SHOT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                        Log.e("Alarm => ", "set");
                    }

                }

            }

        }

    }

}
