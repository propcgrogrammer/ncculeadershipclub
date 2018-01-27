package com.example.tingkuanlin.leadership.NotePad;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class Notepad {

    private String title;
    private int id;
    private String content;
    private String time;

    public Notepad(String title,int id,String content ,String time){
        this.id=id;
        this.title=title;
        this.content=content;
        this.time=time;
    }

    public Notepad(String title,String content,String time){
        this.title=title;
        this.content=content;
        this.time=time;
    }
    public Notepad(int id,String title,String time){
        this.id=id;
        this.title=title;
        this.time=time;
    }
    public Notepad(String title,String content){
        this.title=title;
        this.content=content;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getTimes() {
        return time;
    }

}
