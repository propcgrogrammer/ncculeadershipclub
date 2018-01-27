package com.example.tingkuanlin.leadership.Controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tingkuanlin.leadership.NotePad.Notepad;
import com.example.tingkuanlin.leadership.R;

import java.util.ArrayList;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class NoteAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Notepad> array;
    public NoteAdapter(LayoutInflater inf,ArrayList<Notepad> arry){
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
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=inflater.inflate(R.layout.adapter_list_view, null);
            vh.tv1 =(TextView) convertView.findViewById(R.id.tittle_textview);
            vh.tv2=(TextView) convertView.findViewById(R.id.datetime_textview);
            convertView.setTag(vh);
        }
        vh=(ViewHolder) convertView.getTag();
        vh.tv1.setText(array.get(position).getTitle());
        vh.tv2.setText(array.get(position).getTimes());
        return convertView;
    }
    class ViewHolder{
        TextView tv1,tv2;
    }

}
