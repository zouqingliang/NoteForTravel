package com.liang.pro.notefortravel;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/9/8.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
