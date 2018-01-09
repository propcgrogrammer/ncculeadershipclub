package com.example.tingkuanlin.leadership.Controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.tingkuanlin.leadership.NotePad.Notepad;
import com.example.tingkuanlin.leadership.R;
import com.example.tingkuanlin.leadership.Zen.Zen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by tingkuanlin on 2018/1/8.
 */

public class ZenAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Zen> array;

    public ZenAdapter(LayoutInflater inf,ArrayList<Zen> arry){
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
        ZenAdapter.ViewHolder vh;
        if(convertView==null){
            vh=new ZenAdapter.ViewHolder();
            convertView=inflater.inflate(R.layout.zen_schedule_list_view, null);
            vh.zen =(TextView) convertView.findViewById(R.id.zen_kind_textview);
            vh.start=(TextView) convertView.findViewById(R.id.zen_start_datetime);
            vh.end=(TextView) convertView.findViewById(R.id.zen_end_datetime);

            convertView.setTag(vh);
        }
        vh=(ZenAdapter.ViewHolder) convertView.getTag();

        vh.zen.setText(array.get(position).getTypeContent(array.get(position).getType()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");

        String start_datetime = array.get(position).getStart_date() + " " + array.get(position).getStart_time();
        String end_datetime = array.get(position).getEnd_date() + " " + array.get(position).getEnd_time();

        vh.start.setText("開始時間： " + sdf.format(array.get(position).getStartDate()));
        vh.end.setText("結束時間： " + sdf.format(array.get(position).getEndDate()));

        //vh.start.setText("開始時間： " + start_datetime);
        //vh.end.setText("結束時間： " + end_datetime);
//        vh.start.setText(sdf.format(array.get(position).getStartDate()));
//        vh.end.setText(sdf.format(array.get(position).getEndDate()));
        return convertView;
    }
    class ViewHolder{
        TextView zen,start,end;
    }

}
