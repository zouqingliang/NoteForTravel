package com.liang.pro.notefortravel.model;

public class Account {

    private int id;  //主键id
    private String category;  //消费类别
    private int state;    //1：收入；2：支出
    private float money;  //金额

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
