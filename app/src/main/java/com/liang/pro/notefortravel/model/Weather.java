package com.liang.pro.notefortravel.model;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Weather {

    private String city; //城市
    private String weather;  //天气状况
    private String temperature; //气温

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
