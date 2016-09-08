package com.liang.pro.notefortravel.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(value = R.layout.activity_start)
public class StartActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //在没有ActionBar的情况下（有actionbar的用另外方式），设置状态栏透明
        if (Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity(MainActivity.class);
                StartActivity.this.finish();
            }
        },3000);

    }
}
