package com.liang.pro.notefortravel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.liang.pro.notefortravel.database.TravelDB;

import java.util.List;

public class TravelAdapter extends MyAdapter {

    private class ViewHolder{

    }

    public TravelAdapter(Context context) {
        super(context, null);
        TravelDB travelDB = TravelDB.getInstance(context);
        List list = travelDB.loadTravel();
        setList(list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
