package com.example.tingkuanlin.leadership.Zen;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tingkuanlin on 2018/1/8.
 */

public class Zen {

    private int id = 0;
    private int type = -1;
    private String start_date = null;
    private String start_time = null;
    private String start_date_time = null;
    private String end_date = null;
    private String end_time = null;
    private String end_date_time = null;
    private String remind_date = null;
    private String remind_time = null;
    private String remind_date_time = null;
    private int notify = -1;
    private int notifyTime = -1;
    private int isNotify = 0;
    private int notifyType = -1;

    private Date startDate = null;
    private Date endDate = null;
    private Date remindDate = null;

    private String[] types = {"晨間禪","午間禪","晚間禪","其他"};
    private int[] notifies = {-1,1,5,10,30,60};


    public Zen(int type, String start_date, String start_time,
               String end_date, String end_time, int notify,String remind_date, String remind_time)
    {
        setType(type);
        setStart_date(start_date);
        setStart_time(start_time);
        setEnd_date(end_date);
        setEnd_time(end_time);
        setRemind_date(remind_date);
        setRemind_time(remind_time);
        setNotify(notify);
        setStart_date_time(start_date, start_time);
        setEnd_date_time(end_date, end_time);
        setRemind_date_time(remind_date,remind_time);

        setStartDate();
        setEndDate();
        setRemindDate();
    }
    public Zen(int id, int type, String start_date, String start_time,
               String end_date, String end_time, int notify,String remind_date, String remind_time)
    {
        setId(id);
        setType(type);
        setStart_date(start_date);
        setStart_time(start_time);
        setEnd_date(end_date);
        setEnd_time(end_time);
        setRemind_date(remind_date);
        setRemind_time(remind_time);
        setNotify(notify);
        setStart_date_time(start_date, start_time);
        setEnd_date_time(end_date, end_time);
        setRemind_date_time(remind_date,remind_time);

        setStartDate();
        setEndDate();
        setRemindDate();
    }

    public Zen(int type, String start_date, String start_time,
               String end_date, String end_time, int notify,
               int notifyTime, int isNotify, int notifyType ,String remind_date, String remind_time)
    {
        setType(type);
        setStart_date(start_date);
        setStart_time(start_time);
        setEnd_date(end_date);
        setEnd_time(end_time);
        setRemind_date(remind_date);
        setRemind_time(remind_time);
        setNotify(notify);
        setNotifyTime(notifyTime);
        setIsNotify(isNotify);
        setNotifyType(notifyType);
        setStart_date_time(start_date, start_time);
        setEnd_date_time(end_date, end_time);
        setRemind_date_time(remind_date,remind_time);

        setStartDate();
        setEndDate();
        setRemindDate();
    }
    public Zen(int id, int type, String start_date, String start_time,
               String end_date, String end_time, int notify,
               int notifyTime, int isNotify, int notifyType,String remind_date, String remind_time)
    {
        setId(id);
        setType(type);
        setStart_date(start_date);
        setStart_time(start_time);
        setEnd_date(end_date);
        setEnd_time(end_time);
        setRemind_date(remind_date);
        setRemind_time(remind_time);
        setNotify(notify);
        setNotifyTime(notifyTime);
        setIsNotify(isNotify);
        setNotifyType(notifyType);
        setStart_date_time(start_date, start_time);
        setEnd_date_time(end_date, end_time);
        setRemind_date_time(remind_date,remind_time);

        setStartDate();
        setEndDate();
        setRemindDate();
    }


    public void setId(int id){this.id = id;}
    public void setType(int type){this.type = type;}
    public void setStart_date(String start_date){this.start_date = start_date;}
    public void setStart_time(String start_time){this.start_time = start_time;}
    public void setStart_date_time(String start_date, String start_time){
        this.start_date_time = start_date + " " + start_time;
    }
    public void setEnd_date(String end_date){this.end_date = end_date;}
    public void setEnd_time(String end_time){this.end_time = end_time;}
    public void setEnd_date_time(String end_date, String end_time){
        this.end_date_time = end_date + " " + end_time;
    }
    public void setRemind_date(String remind_date){this.remind_date = remind_date;}
    public void setRemind_time(String remind_time){this.remind_time = remind_time;}
    public void setRemind_date_time(String remind_date, String remind_time){
        this.remind_date_time = remind_date + " " + remind_time;
    }
    public void setNotify(int notify){this.notify = notify;}
    public void setNotifyTime(int time){this.notifyTime = time;}
    public void setNotifyType(int type){this.notifyType = type;}
    public void setIsNotify(int isNotify){this.isNotify = isNotify;}
    public void setStartDate(){

        try {

            Log.d("String => ",this.start_date_time);

            //定義好時間字串的格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //將字串轉成Date型
            Date dt = sdf.parse(this.start_date_time);
            this.startDate = dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setEndDate(){

        try {

            Log.d("String => ",this.end_date_time);

            //定義好時間字串的格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //將字串轉成Date型
            Date dt = sdf.parse(end_date_time);
            this.endDate = dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setRemindDate(){

        try {

            Log.d("String => ",this.remind_date_time);

            //定義好時間字串的格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //將字串轉成Date型
            Date dt = sdf.parse(remind_date_time);
            this.remindDate = dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getId(){return this.id;}
    public String getTypeContent(int index){
        if(index >= types.length) return "";
        return types[index].toString();
    }

    public int getType(){return this.type;}
    public String getStart_date(){return this.start_date;}
    public String getStart_time(){return this.start_time;}
    public String getStart_date_time(){return this.start_date_time;}
    public String getEnd_date(){return this.end_date;}
    public String getEnd_time(){return this.end_time;}
    public String getRemind_date(){return this.remind_date;}
    public String getRemind_time(){return this.remind_time;}
    public String getRemind_date_time(){return this.remind_date_time;}


    public Date getStartDate(){return this.startDate;}
    public Date getEndDate(){return this.endDate;}
    public Date getRemindDate(){return this.remindDate;}
    public int getNotify(){return this.notify;}
    public int getNotifyTime(){return this.notifyTime;}
    public int getNotifyType(){return this.notifyType;}
    public int getIsNotify(){return this.isNotify;}

    public Date convertFromStringToDate(String date){


        try {
            //定義好時間字串的格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //將字串轉成Date型
            return sdf.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
