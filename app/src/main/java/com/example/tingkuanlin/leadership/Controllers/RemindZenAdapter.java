package com.example.tingkuanlin.leadership.Controllers;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tingkuanlin.leadership.R;
import com.example.tingkuanlin.leadership.Zen.RemindZen;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tingkuanlin on 2018/1/9.
 */

public class RemindZenAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Zen> array;


    public RemindZenAdapter(LayoutInflater inf,ArrayList<Zen> arry){
        this.inflater=inf;
        this.array=arry;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        RemindZenAdapter.ViewHolder vh;
        if(convertView==null){
            vh=new RemindZenAdapter.ViewHolder();
            convertView=inflater.inflate(R.layout.zen_remind_list_view, null);
            vh.zen =(TextView) convertView.findViewById(R.id.remind_kind_textview);
            vh.start=(TextView) convertView.findViewById(R.id.remind_start_datetime);
            vh.end=(TextView) convertView.findViewById(R.id.remind_end_datetime);
            vh.remind=(TextView) convertView.findViewById(R.id.remind_datetime);

            convertView.setTag(vh);
        }
        vh=(RemindZenAdapter.ViewHolder) convertView.getTag();

        vh.zen.setText(array.get(position).getTypeContent(array.get(position).getType()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");

        String start_datetime = array.get(position).getStart_date() + " " + array.get(position).getStart_time();
        String end_datetime = array.get(position).getEnd_date() + " " + array.get(position).getEnd_time();
        String remind_datetime = array.get(position).getRemind_date() + " " + array.get(position).getRemind_time();
        int notify = array.get(position).getNotify();


        if (notify == 1) {

            Date now = new Date();

            if(array.get(position).getRemindDate().before(now)){
                vh.start.setText("開始時間： " + sdf.format(array.get(position).getStartDate()));
                vh.end.setText("結束時間： " + sdf.format(array.get(position).getEndDate()));
                vh.remind.setTextColor(Color.RED);
                vh.remind.setText("提醒時間： 已過期");
            }
            else {

                vh.start.setText("開始時間： " + sdf.format(array.get(position).getStartDate()));
                vh.end.setText("結束時間： " + sdf.format(array.get(position).getEndDate()));
                vh.remind.setTextColor(Color.rgb(1,126,72));
                vh.remind.setText("提醒時間： " + sdf.format(array.get(position).getRemindDate()));
            }
        } else {
            vh.start.setText("開始時間： " + sdf.format(array.get(position).getStartDate()));
            vh.end.setText("結束時間： " + sdf.format(array.get(position).getEndDate()));
            vh.remind.setTextColor(Color.rgb(133,66,0));
            vh.remind.setText("提醒時間： 無設定提醒時間");
        }


        //vh.start.setText("開始時間： " + start_datetime);
        //vh.end.setText("結束時間： " + end_datetime);
//        vh.start.setText(sdf.format(array.get(position).getStartDate()));
//        vh.end.setText(sdf.format(array.get(position).getEndDate()));
        return convertView;
    }
    class ViewHolder{
        TextView zen,start,end,remind;
    }

}
