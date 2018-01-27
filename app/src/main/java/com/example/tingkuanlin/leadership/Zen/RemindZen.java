package com.example.tingkuanlin.leadership.Zen;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tingkuanlin on 2018/1/9.
 */

public class RemindZen {

    private int id = 0;
    private String remind_date = null;
    private String remind_time = null;
    private String remind_date_time = null;
    private int notify = -1;

    private Date remindDate = null;


    public RemindZen(int notify, String remind_date, String remind_time)
    {

        setRemind_date(remind_date);
        setRemind_time(remind_time);
        setNotify(notify);
        setRemind_date_time(remind_date, remind_time);
        setRemindDate();

    }
    public RemindZen(int id, int notify, String remind_date, String remind_time)
    {
        setId(id);
        setRemind_date(remind_date);
        setRemind_time(remind_time);
        setNotify(notify);
        setRemind_date_time(remind_date, remind_time);
        setRemindDate();
    }

    public void setId(int id){this.id = id;}
    public void setRemind_date(String remind_date){this.remind_date = remind_date;}
    public void setRemind_time(String remind_time){this.remind_time = remind_time;}
    public void setRemind_date_time(String remind_date, String remind_time){
        this.remind_date_time = remind_date + " " + remind_time;
    }
    public void setNotify(int notify){this.notify = notify;}
    public void setRemindDate(){

        try {

            //定義好時間字串的格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //將字串轉成Date型
            Date dt = sdf.parse(this.remind_date_time);
            this.remindDate = dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getId(){return this.id;}
    public String getRemind_date(){return this.remind_date;}
    public String getRemind_time(){return this.remind_time;}
    public Date getRemindDate(){return this.remindDate;}
    public int getNotify(){return this.notify;}


}
