package com.liang.pro.notefortravel.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Utility {

    public List<Map<String, Object>> getJsonDate(String response){
        JSONObject jsonObject = null;
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        try {
            jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject("result");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cityName", weatherInfo.optString("city"));
            map.put("weather", weatherInfo.optString("weather"));
            map.put("temp", weatherInfo.optString("temperature"));
            all.add(map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return all;
    }
}
