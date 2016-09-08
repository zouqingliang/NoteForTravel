package com.liang.pro.notefortravel.activity.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/8.
 */
public class BaseActivity extends AppCompatActivity {

    public void showMsg(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void openActivity(Class<?> pClass){
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
    }
}
