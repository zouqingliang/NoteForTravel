package com.liang.pro.notefortravel.model;

public class Travel {
   /* CREATE TABLE [travel] (
            [_id] INT NOT NULL,
            [title] TEXT NOT NULL,
            [content] TEXT NOT NULL,
            [write_time] TIME NOT NULL,
            [travel_time] TIME NOT NULL,
            [place] TEXT NOT NULL,
            [img_path] TEXT,
    CONSTRAINT [] PRIMARY KEY ([_id]));*/

    private int id;   //主键id
    private String title;  //标题
    private String content;  //内容
    private String write_time;  //编辑笔记时间
    private String travel_time;  //旅行时间
    private String destination;  //目的地
    private String img_path;  //图片地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrite_time() {
        return write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
