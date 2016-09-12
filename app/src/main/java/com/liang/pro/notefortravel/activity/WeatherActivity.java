package com.liang.pro.notefortravel.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liang.pro.notefortravel.R;
import com.liang.pro.notefortravel.activity.base.BaseActivity;
import com.liang.pro.notefortravel.utils.HttpUtil;
import com.liang.pro.notefortravel.utils.Utility;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.tool_weather)
public class WeatherActivity extends BaseActivity {

    @ViewInject(R.id.edit_city)
    private EditText edit_city;  //查询框

    @ViewInject(R.id.btn_search)
    private Button btn_city;  //查询城市

    @ViewInject(R.id.tv_city)
    private TextView tv_city;

    @ViewInject(R.id.weather_desp)
    private TextView tv_weather;

    @ViewInject(R.id.temperature)
    private TextView tv_temperature;
    
    private String url = "http://v.juhe.cn/weather/index?cityname=";
    private String key = "&key=fee8b612fabf8251afd4e2d6fb033ea8";

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Utility utility = new Utility();
                    try {
                        List<Map<String, Object>> all = utility.getJsonDate(msg.obj.toString());
                        Iterator<Map<String, Object>> iterator = all.iterator();
                        while (iterator.hasNext()) {
                            Map<String, Object> map = iterator.next();
                            Log.d("天气", map.get("weather").toString());
                            tv_city.setText(map.get("cityName").toString());
                            tv_weather.setText(map.get("weather").toString());
                            tv_temperature.setText(map.get("temperature").toString());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(R.id.btn_search)
    private void doClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = url + edit_city.getText().toString() + key;
                Log.d("test",address);
                HttpUtil httpUtil = new HttpUtil();
                String jsonString = httpUtil.sendHttpRequest(address);
                Message message = handler.obtainMessage(1,jsonString);
                handler.sendMessage(message);
            }
        }).start();
    }

}
