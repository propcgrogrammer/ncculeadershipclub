package com.example.tingkuanlin.leadership;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.tingkuanlin.leadership.Controllers.NoteAdapter;
import com.example.tingkuanlin.leadership.DBHelpers.RemindZenDBOperators;
import com.example.tingkuanlin.leadership.DBHelpers.ZenDBOperators;
import com.example.tingkuanlin.leadership.NotePad.Notepad;
import com.example.tingkuanlin.leadership.Receiver.AlarmReceiver;
import com.example.tingkuanlin.leadership.Zen.RemindZen;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by tingkuanlin on 2018/1/8.
 */

public class ZenSettingActivity extends AppCompatActivity {


    private int id = -1;
    private Spinner zen_kind = null;
    private EditText start_date = null;
    private EditText start_time = null;
    private EditText end_date = null;
    private EditText end_time = null;
    private EditText remind_date = null;
    private EditText remind_time = null;
    private CheckBox zen_notify = null;

    private Button confirm = null;
    private Button cancel = null;

    private int mYear, mMonth, mDay;
    private int hour,minute;

    ZenDBOperators zenDBOperators = null;
    RemindZenDBOperators remindZenDBOperators = null;
    Zen zen = null;
    RemindZen remindZen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen_detailed);

        zen_kind = (Spinner) findViewById(R.id.zen_kind_spinner);
        zen_notify = (CheckBox) findViewById(R.id.remind_checkBox);

        start_date = (EditText) findViewById(R.id.start_date);
        start_time = (EditText) findViewById(R.id.start_time);
        end_date = (EditText) findViewById(R.id.end_date);
        end_time = (EditText) findViewById(R.id.end_time);
        remind_date = (EditText) findViewById(R.id.remind_date);
        remind_time = (EditText) findViewById(R.id.remind_time);


        remind_date.setEnabled(false);
        remind_time.setEnabled(false);


        zenDBOperators = new ZenDBOperators(this);
        remindZenDBOperators = new RemindZenDBOperators(this);
        Intent intent=this.getIntent();

        id=intent.getIntExtra("id", 0);

        if(id!=0){
            zen= zenDBOperators.getZen(id);

            zen_kind.setSelection(zen.getType());
            if(zen.getNotify() == 1)  zen_notify.setChecked(true);
            else  zen_notify.setChecked(false);
            start_date.setText(zen.getStart_date());
            start_time.setText(zen.getStart_time());
            end_date.setText(zen.getEnd_date());
            end_time.setText(zen.getEnd_time());

            ////////////////////////////////////////////
            remind_date.setText(zen.getRemind_date());
            remind_time.setText(zen.getRemind_time());

        }

        zen_notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()) {
                    remind_date.setEnabled(true);
                    remind_time.setEnabled(true);
                }else{
                    remind_date.setEnabled(false);
                    remind_time.setEnabled(false);
                }
            }
        });


        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ZenSettingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = setDateFormat(year,month,day);
                        start_date.setText(format);
                    }

                }, mYear,mMonth, mDay).show();

            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ZenSettingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = setDateFormat(year,month,day);
                        end_date.setText(format);
                    }

                }, mYear,mMonth, mDay).show();

            }
        });

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                new TimePickerDialog(ZenSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        String format = setTimeFormat(hour,minute);
                        start_time.setText(format);

                    }
                }, hour,minute,true).show();
            }
        });


        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                new TimePickerDialog(ZenSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        String format = setTimeFormat(hour,minute);
                        end_time.setText(format);

                    }
                }, hour,minute,true).show();

            }
        });

        remind_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ZenSettingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = setDateFormat(year,month,day);
                        remind_date.setText(format);
                    }

                }, mYear,mMonth, mDay).show();

            }
        });

        remind_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                new TimePickerDialog(ZenSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        String format = setTimeFormat(hour,minute);
                        remind_time.setText(format);

                    }
                }, hour,minute,true).show();

            }
        });


        confirm = (Button) findViewById(R.id.zen_add_confirm_btn);
        cancel = (Button) findViewById(R.id.zen_cancel_btn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(zen_notify.isChecked()) {

                    if (!isDateFormat(remind_date.getText().toString())) return;
                    if (!isTimeFormat(remind_time.getText().toString())) return;
                }

                if(isEmpty()) return;

                if(!isDateFormat(start_date.getText().toString())) return;
                if(!isDateFormat(end_date.getText().toString())) return;

                if(!isTimeFormat(start_time.getText().toString())) return;
                if(!isTimeFormat(end_time.getText().toString())) return;


                if(!islegalDate()) return;




                int type = zen_kind.getSelectedItemPosition();
                String startDate = start_date.getText().toString();
                String startTime = start_time.getText().toString();
                String endDate = end_date.getText().toString();
                String endTime = end_time.getText().toString();
                String remindDate = remind_date.getText().toString();
                String remindTime = remind_time.getText().toString();
                int notify = -1;
                if(zen_notify.isChecked()) notify = 1;
                else   notify = 0;

                if(id!=0){
                    zen=new Zen(id,type, startDate, startTime, endDate, endTime, notify,remindDate,remindTime);
                    zenDBOperators.toUpdate(zen);

                    if(zen_notify.isChecked()) registForAlarm(id,remindDate,remindTime);

                    new AlertDialog.Builder(ZenSettingActivity.this)
                            .setMessage("修改成功")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent=new Intent(ZenSettingActivity.this,ZenScheduleActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
                }
                else{
                    zen=new Zen(type, startDate, startTime, endDate, endTime, notify, remindDate, remindTime);
                    zenDBOperators.toInsert(zen);

                    if(zen_notify.isChecked()) registForAlarm(id,remindDate,remindTime);

                    new AlertDialog.Builder(ZenSettingActivity.this)
                            .setMessage("新增成功")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(ZenSettingActivity.this,ZenScheduleActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();

                }



            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(ZenSettingActivity.this,ZenScheduleActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        //TODO Auto-generated method stub
        Intent intent=new Intent(ZenSettingActivity.this,ZenScheduleActivity.class);
        startActivity(intent);
        finish();
    }

    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }
    private String setTimeFormat(int hour, int minute){

        String hr = String.valueOf(hour);
        String min = String.valueOf(minute);

        StringBuilder sb = new StringBuilder();

        if(hr.length() == 1) sb.append("0");
        sb.append(hr);
        sb.append(":");
        if(min.length() == 1) sb.append("0");
        sb.append(min);
        sb.append(":00");
        return sb.toString();
    }

    private boolean isDateFormat(String date){
        String pattern = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date legalDate = sdf.parse(date);
            return true;
        }catch (Exception e){

            new AlertDialog.Builder(ZenSettingActivity.this)
                    .setMessage("日期格式錯誤")
                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        }
        return false;
    }
    private boolean isTimeFormat(String time){


        String pattern = "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$";

        if(time.matches(pattern)) return true;
        else{
            new AlertDialog.Builder(ZenSettingActivity.this)
                    .setMessage("時間格式錯誤")
                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        }
        return false;

    }
    private boolean isEmpty(){

        if(start_date.getText().equals("")){

            new AlertDialog.Builder(ZenSettingActivity.this)
                    .setMessage("請填入開始日期")
                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();

            return true;
        }
        if(start_time.getText().equals("")){

            new AlertDialog.Builder(ZenSettingActivity.this)
                    .setMessage("請填入開始時間")
                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();

            return true;
        }
        if(end_date.getText().equals("")){

            new AlertDialog.Builder(ZenSettingActivity.this)
                    .setMessage("請填入結束日期")
                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();

            return true;
        }
        if(end_time.getText().equals("")){

            new AlertDialog.Builder(ZenSettingActivity.this)
                    .setMessage("請填入結束時間")
                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();

            return true;
        }
        if(zen_notify.isChecked()){

            if(remind_date.getText().equals("")){

                new AlertDialog.Builder(ZenSettingActivity.this)
                        .setMessage("請填入提醒日期")
                        .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();

                return true;
            }
            if(remind_time.getText().equals("")){

                new AlertDialog.Builder(ZenSettingActivity.this)
                        .setMessage("請填入提醒時間")
                        .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();

                return true;
            }

        }

        return false;
    }
    private boolean islegalDate(){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String start = "";
        String end = "";
        String remind = "";


        if(!start_date.getText().equals("") && !start_time.getText().equals(""))
            start = start_date.getText().toString() + " " + start_time.getText().toString();
        if(!end_date.getText().equals("") && !end_time.getText().equals(""))
            end = end_date.getText().toString() + " " + end_time.getText().toString();
        if(!remind_date.getText().equals("") && !remind_time.getText().equals(""))
            remind = remind_date.getText().toString() + " " + remind_time.getText().toString();

        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            Date now = new Date();

            Date remindDate = null;
            if(zen_notify.isChecked())  remindDate = sdf.parse(remind);


            if(startDate.after(now)) {
                if (startDate.before(endDate)) {

                    if (zen_notify.isChecked()) {

                        if (remindDate.before(startDate)) {


                            if (remindDate.after(now))
                                return true;
                            else {

                                new AlertDialog.Builder(ZenSettingActivity.this)
                                        .setMessage("提醒時間必須晚於現在時間")
                                        .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .create().show();
                            }
                        } else {

                            new AlertDialog.Builder(ZenSettingActivity.this)
                                    .setMessage("提醒時間必須早於開始時間")
                                    .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .create().show();
                        }

                    } else {
                        return true;
                    }

                } else {
                    new AlertDialog.Builder(ZenSettingActivity.this)
                            .setMessage("結束時間必須晚於開始時間")
                            .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .create().show();
                }
            }else{

                new AlertDialog.Builder(ZenSettingActivity.this)
                        .setMessage("開始時間必須晚於現在時間")
                        .setPositiveButton("我瞭解了", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();

            }
            return false;



        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }



    }
    private void registForAlarm(int id, String date, String time){

        StringTokenizer stringTokenizer = new StringTokenizer(date,"-");
        int year = Integer.parseInt(stringTokenizer.nextToken());
        int month = Integer.parseInt(stringTokenizer.nextToken());
        int day = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(time,":");
        int hour = Integer.parseInt(stringTokenizer.nextToken());
        int min = Integer.parseInt(stringTokenizer.nextToken());
        int sec = Integer.parseInt(stringTokenizer.nextToken());

        String dat = year+"/"+(month-1)+"/"+day+" "+hour+":"+min+":"+sec;
        Log.e("date -> ",dat);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day, hour, min, sec);
        Intent intent = new Intent(this, AlarmReceiver.class);


        intent.putExtra("start_date_time",zen.getStart_date_time());

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,id,intent,PendingIntent.FLAG_ONE_SHOT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

        Log.e("Alarm => ", "set");

    }

}
